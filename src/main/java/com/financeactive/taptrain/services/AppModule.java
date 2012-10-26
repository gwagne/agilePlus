package com.financeactive.taptrain.services;

import com.financeactive.taptrain.bindings.FormatBindingFactory;
import com.financeactive.taptrain.pages.About;
import com.financeactive.taptrain.pages.Contact;
import com.financeactive.taptrain.pages.Index;
import com.financeactive.taptrain.pages.users.IndexUsers;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.beanvalidator.BeanValidatorConfigurer;
import org.apache.tapestry5.beanvalidator.BeanValidatorSource;
import org.apache.tapestry5.hibernate.HibernateSymbols;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.BindingSource;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;

import static java.lang.Boolean.TRUE;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule {

    public static void bind(ServiceBinder binder) {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
        binder.bind(Sections.class);
        binder.bind(TapTrainRealm.class);
    }

    public void contributeFactoryDefaults(
            MappedConfiguration<String, Object> configuration) {
        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions. This overrides Tapesty's default
        // (a random hexadecimal number), but may be further overriden by DevelopmentModule or
        // QaModule.
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
        configuration.override(HibernateSymbols.EARLY_START_UP, TRUE.toString());
    }

    public void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration) {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "secret");
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "fr,en");
    }

    public BeanValidatorConfigurer buildBeanValidatorConfigurer() {
        return new BeanValidatorConfigurer() {
            @Override
            public void configure(javax.validation.Configuration<?> configuration) {
                configuration.ignoreXmlConfiguration();
            }
        };
    }

    @Contribute(BeanValidatorSource.class)
    public void contributeBeanValidatorSource(OrderedConfiguration<BeanValidatorConfigurer> configuration, @Local BeanValidatorConfigurer beanValidatorConfigurer) {
        configuration.add("BeanValidator", beanValidatorConfigurer);
    }

    @Contribute(BindingSource.class)
    public void contributeBindingSource(
            MappedConfiguration<String, BindingFactory> configuration, BindingSource bindingSource) {
        configuration.add("format", new FormatBindingFactory(bindingSource));
    }

    @Contribute(JavaScriptStackSource.class)
    public void contributeJavaScriptStackSource(
            MappedConfiguration<String, JavaScriptStack> configuration) {
        configuration.add(TapTrainStack.NAME, new TapTrainStack());
    }

    @Contribute(Sections.class)
    public void contributeSections(OrderedConfiguration<Section> configuration, @Inject ComponentClassResolver componentClassResolver) {
        configuration.add("indexUsers", new Section(componentClassResolver, "Users", IndexUsers.class), "after:index");
        configuration.add("index", new Section(componentClassResolver, "Index", Index.class));
        configuration.add("about", new Section(componentClassResolver, "About", About.class));
        configuration.add("contact", new Section(componentClassResolver, "Contact", Contact.class));
    }

    @Contribute(WebSecurityManager.class)
    public void contributeWebSecurityManager(Configuration<Realm> configuration, TapTrainRealm tapTrainRealm) {
        configuration.add(tapTrainRealm);
    }
}
