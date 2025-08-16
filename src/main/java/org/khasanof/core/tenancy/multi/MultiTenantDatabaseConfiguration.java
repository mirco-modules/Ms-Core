package org.khasanof.core.tenancy.multi;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.khasanof.core.annotation.Common;
import org.khasanof.core.annotation.Tenancy;
import org.khasanof.core.config.RootDataSourceProperties;
import org.khasanof.core.migration.core.database.DatabaseCreatorService;
import org.khasanof.core.service.hikari.HikariDataSourceConfigurerService;
import org.khasanof.core.service.scanner.DynamicClassScanningComponentProvider;
import org.khasanof.core.tenancy.multi.condition.MultiTenantCondition;
import org.khasanof.core.tenancy.multi.helper.DatabaseNameHelper;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseService;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseServiceImpl;
import org.khasanof.core.tenancy.multi.liquibase.resolver.LiquibaseChangelogResolver;
import org.khasanof.core.tenancy.multi.liquibase.resolver.LiquibaseChangelogResolverImpl;
import org.khasanof.core.tenancy.multi.manager.DataSourceManager;
import org.khasanof.core.tenancy.multi.manager.DataSourceManagerImpl;
import org.khasanof.core.tenancy.multi.resolver.TenantIdentifierResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolverImpl;
import org.khasanof.core.tenancy.multi.resolver.datasource.url.DataSourceUrlResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.url.DefaultDataSourceUrlResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi
 * @since 11/2/2024 3:27 PM
 */
@Configuration
@RequiredArgsConstructor
@Conditional({MultiTenantCondition.class})
public class MultiTenantDatabaseConfiguration {

    public static final String COMMON_DATA_SOURCE = "commonDataSource";
    public static final String MULTI_DATA_SOURCE = "tenantDataSource";

    private final DatabaseNameHelper databaseNameHelper;
    private final DatabaseCreatorService databaseCreatorService;
    private final RootDataSourceProperties rootDataSourceProperties;
    private final HikariDataSourceConfigurerService hikariDataSourceConfigurerService;

    /**
     *
     * @return
     */
    @Bean
    public DataSourceManager dataSourceManager() {
        return new DataSourceManagerImpl(liquibaseService(), dataSourceResolver(), getTenantDbProperties(), dataSourceUrlResolver(), databaseCreatorService);
    }

    /**
     *
     * @return
     */
    @Bean
    public LiquibaseService liquibaseService() {
        return new LiquibaseServiceImpl(dataSourceResolver(), liquibaseChangelogResolver());
    }

    /**
     *
     * @return
     */
    @Bean
    public TenantIdentifierResolver tenantIdentifierResolver() {
        return new TenantIdentifierResolver();
    }

    /**
     *
     * @return
     */
    @Bean
    public DataSourceResolver dataSourceResolver() {
        return new DataSourceResolverImpl(getTenantDbProperties(), dataSourceUrlResolver(), rootDataSourceProperties, hikariDataSourceConfigurerService);
    }

    /**
     *
     * @return
     */
    @Bean
    public DataSourceUrlResolver dataSourceUrlResolver() {
        return new DefaultDataSourceUrlResolver(databaseNameHelper, getTenantDbProperties());
    }

    /**
     *
     * @return
     */
    DataSourceProperties getTenantDbProperties() {
        return rootDataSourceProperties.getTenant();
    }

    /**
     *
     * @return
     */
    LiquibaseChangelogResolver liquibaseChangelogResolver() {
        return new LiquibaseChangelogResolverImpl();
    }

    @Configuration
    @EnableTransactionManagement
    @EntityScan("org.khasanof.*")
    @EnableJpaRepositories(
            basePackages = "org.khasanof",
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.REGEX,
                    pattern = ".*\\.repository\\.common\\..*"
            )
    )
    @Conditional({MultiTenantCondition.class})
    public static class CommonDataSourcesConfiguration extends AbstractRootDataSourcesConfiguration {

        public static final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";

        public CommonDataSourcesConfiguration(RootDataSourceProperties rootDataSourceProperties,
                                              HikariDataSourceConfigurerService hikariDataSourceConfigurerService,
                                              DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider
        ) {
            super(rootDataSourceProperties, hikariDataSourceConfigurerService, dynamicClassScanningComponentProvider);
        }

        /**
         *
         * @return
         */
        @Bean
        @Primary
        public DataSource commonDataSource() {
            DataSource dataSource = rootDataSourceProperties.getCommon()
                    .initializeDataSourceBuilder()
                    .build();
            if (dataSource instanceof HikariDataSource hikariDataSource) {
                setMsDataSourceProperties(hikariDataSource, rootDataSourceProperties.getCommonProperties());
            }
            return dataSource;
        }

        /**
         *
         * @param dataSource
         * @param builder
         * @return
         */
        @Primary
        @Bean(name = ENTITY_MANAGER_FACTORY)
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                @Qualifier(COMMON_DATA_SOURCE) DataSource dataSource,
                EntityManagerFactoryBuilder builder
        ) {
            List<Class<?>> managedEntities = getEntitiesNames(new AnnotationTypeFilter(Common.class));
            LocalContainerEntityManagerFactoryBean factoryBean = builder.dataSource(dataSource)
                    .persistenceUnit("common")
                    .packages(managedEntities.toArray(new Class<?>[0]))
                    .build();

            factoryBean.setJpaPropertyMap(createJpaProperties());
            return factoryBean;
        }

        /**
         *
         * @param entityManagerFactory
         * @return
         */
        @Bean
        @Primary
        public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }
    }

    @Configuration
    @EnableTransactionManagement
    @EntityScan("org.khasanof.*")
    @EnableJpaRepositories(
            basePackages = "org.khasanof",
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.REGEX,
                    pattern = ".*\\.repository\\.tenancy\\..*"
            ),
            entityManagerFactoryRef = TenantDataSourcesConfiguration.ENTITY_MANAGER_FACTORY,
            transactionManagerRef = "tenantTransactionManager"
    )
    @Conditional({MultiTenantCondition.class})
    public static class TenantDataSourcesConfiguration extends AbstractRootDataSourcesConfiguration {

        public static final String ENTITY_MANAGER_FACTORY = "multiDbEntityManagerFactory";

        private final DataSourceManager dataSourceManager;
        private final TenantIdentifierResolver tenantIdentifierResolver;

        public TenantDataSourcesConfiguration(
                DataSourceManager dataSourceManager,
                TenantIdentifierResolver tenantIdentifierResolver,
                RootDataSourceProperties rootDataSourceProperties,
                HikariDataSourceConfigurerService hikariDataSourceConfigurerService,
                DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider
        ) {
            super(rootDataSourceProperties, hikariDataSourceConfigurerService, dynamicClassScanningComponentProvider);
            this.dataSourceManager = dataSourceManager;
            this.tenantIdentifierResolver = tenantIdentifierResolver;
        }

        /**
         *
         * @return
         */
        @Bean
        public DataSource tenantDataSource() {
            DataSource dataSource = rootDataSourceProperties.getTenant()
                    .initializeDataSourceBuilder()
                    .build();

            if (dataSource instanceof HikariDataSource hikariDataSource) {
                setMsDataSourceProperties(hikariDataSource, rootDataSourceProperties.getTenantProperties());
            }

            var multiTenantDataSource = new MultiTenantDataSource(dataSource, dataSourceManager, tenantIdentifierResolver);
            multiTenantDataSource.setDefaultTargetDataSource(dataSource);
            multiTenantDataSource.setTargetDataSources(Collections.emptyMap());
            multiTenantDataSource.afterPropertiesSet();

            return multiTenantDataSource;
        }

        /**
         *
         * @param dataSource
         * @param builder
         * @return
         */
        @Bean(name = ENTITY_MANAGER_FACTORY)
        public LocalContainerEntityManagerFactoryBean tenantDbEntityManagerFactory(
                @Qualifier(MULTI_DATA_SOURCE) DataSource dataSource,
                EntityManagerFactoryBuilder builder
        ) {
            List<Class<?>> managedEntities = getEntitiesNames(new AnnotationTypeFilter(Tenancy.class));
            LocalContainerEntityManagerFactoryBean factoryBean = builder.dataSource(dataSource)
                    .persistenceUnit("tenant")
                    .packages(managedEntities.toArray(new Class<?>[0]))
                    .build();

            factoryBean.setJpaPropertyMap(createJpaProperties());
            return factoryBean;
        }

        /**
         *
         * @param entityManagerFactory
         * @return
         */
        @Bean
        public PlatformTransactionManager tenantTransactionManager(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }
    }

    public static abstract class AbstractRootDataSourcesConfiguration {

        protected final RootDataSourceProperties rootDataSourceProperties;
        protected final HikariDataSourceConfigurerService hikariDataSourceConfigurerService;
        protected final DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider;

        public AbstractRootDataSourcesConfiguration(RootDataSourceProperties rootDataSourceProperties,
                                                    HikariDataSourceConfigurerService hikariDataSourceConfigurerService,
                                                    DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider
        ) {
            this.rootDataSourceProperties = rootDataSourceProperties;
            this.hikariDataSourceConfigurerService = hikariDataSourceConfigurerService;
            this.dynamicClassScanningComponentProvider = dynamicClassScanningComponentProvider;
        }

        /**
         *
         * @param typeFilter
         * @return
         */
        protected List<Class<?>> getEntitiesNames(TypeFilter typeFilter) {
            return dynamicClassScanningComponentProvider.findClasses(typeFilter);
        }

        /**
         *
         * @return
         */
        protected Map<String, Object> createJpaProperties() {
            Map<String, Object> jpaProperties = new HashMap<>();
            jpaProperties.put("hibernate.jdbc.time_zone", "UTC");
            jpaProperties.put("hibernate.timezone.default_storage", "NORMALIZE");
            jpaProperties.put("hibernate.type.preferred_instant_jdbc_type", "TIMESTAMP");
            jpaProperties.put("hibernate.id.new_generator_mappings", true);
            jpaProperties.put("hibernate.connection.provider_disables_autocommit", false);
            jpaProperties.put("hibernate.cache.use_second_level_cache", false);
            jpaProperties.put("hibernate.cache.use_query_cache", false);
            jpaProperties.put("hibernate.generate_statistics", false);
            jpaProperties.put("hibernate.jdbc.batch_size", 25);
            jpaProperties.put("hibernate.order_inserts", true);
            jpaProperties.put("hibernate.order_updates", true);
            jpaProperties.put("hibernate.query.fail_on_pagination_over_collection_fetch", true);
            jpaProperties.put("hibernate.query.in_clause_parameter_padding", true);

            jpaProperties.put("hibernate.hbm2ddl.auto", "none");
            jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
            jpaProperties.put("hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
            return jpaProperties;
        }

        /**
         *
         * @param hikariDataSource
         * @param properties
         */
        protected void setMsDataSourceProperties(HikariDataSource hikariDataSource, MsDataSourceProperties properties) {
            hikariDataSourceConfigurerService.configurer(hikariDataSource, properties);
        }
    }
}
