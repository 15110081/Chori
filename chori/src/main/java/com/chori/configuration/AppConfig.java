package com.chori.configuration;

import java.io.IOException;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.chori.validator.AccessoryConsumptionValidator;
import com.chori.validator.AccessoryGroupValidator;
import com.chori.validator.AccessoryPriceValidator;
import com.chori.validator.AccessorySupplierContactValidator;
import com.chori.validator.AccessorySupplierValidator;
import com.chori.validator.AccessoryValidator;
import com.chori.validator.AgentValidator;
import com.chori.validator.ColorValidator;
import com.chori.validator.CtnrtypeValidator;
import com.chori.validator.CurrencyValidator;
import com.chori.validator.CurrencyexchangeValidator;
import com.chori.validator.CustomerContactValidator;
import com.chori.validator.CustomerValidator;
import com.chori.validator.DestinationValidator;
import com.chori.validator.EstimatetimeValidator;
import com.chori.validator.FabricsupplierValidator;
import com.chori.validator.FactoryValidator;
//import com.chori.validator.FactoryValidator;
import com.chori.validator.GarmentkindValidator;
import com.chori.validator.GarmentstyleValidator;
import com.chori.validator.GarmentstyleaccessorydetailValidator;
import com.chori.validator.ShippingLineContactValidator;
import com.chori.validator.ShippingLineValidator;
import com.chori.validator.TypeValidator;
import com.chori.validator.UnitValidator;
import com.chori.validator.UserValidator;
import com.chori.validator.WidthValidator;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.chori")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();

		// Set the maximum allowed size (in bytes) for each individual file.
		resolver.setMaxUploadSize(52428800);// 50MB

		// You may also set other available properties.

		return resolver;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	// @Bean
	// public MessageSource messageSource() {
	// ResourceBundleMessageSource messageSource = new
	// ResourceBundleMessageSource();
	// messageSource.setBasename("messages");
	// return messageSource;
	// }

	// equivalents for <mvc:resources/> tags
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/admin-theme/**").addResourceLocations(
				"/resources/themes/admin-theme/");
		registry.addResourceHandler("/images/**").addResourceLocations(
				"/resources/img/");
		registry.addResourceHandler("/css/**").addResourceLocations(
				"/resources/css/");
		registry.addResourceHandler("/js/**").addResourceLocations(
				"/resources/js/");
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
		// registry.addResourceHandler("/choriAccessoryImage/**").addResourceLocations("file:/C:/Chori/AccessoryImage/");
		// registry.addResourceHandler("/choriAccessoryImage/**")
		// .addResourceLocations("/resources/chori/AccessoryImage/");

		// registry.addResourceHandler("/choriSignatureImage/**")
		// .addResourceLocations("/resources/chori/SignatureImage/");
		// registry.addResourceHandler("/choriPackingGuide/**")
		// .addResourceLocations("/resources/chori/PackingGuide/");
		// registry.addResourceHandler("/choriPiattachFile/**")
		// .addResourceLocations("/resources/chori/PiattachFile/");
		// registry.addResourceHandler("/choriManufactureGuide/**")
		// .addResourceLocations("/resources/chori/ManufactureGuide/");

		// registry.addResourceHandler("/choriGarmentStyleImage/**")
		// .addResourceLocations("/resources/chori/GarmentStyleImage/");
		
		// chori garment style image
//		registry.addResourceHandler("/choriGarmentStyleImage/**")
//				.addResourceLocations(
//						messageSource().getMessage("garmentStyleImageLocation",
//								null, null).contains(":") ? "file:/"
//								+ messageSource()
//										.getMessage(
//												"garmentStyleImageLocation",
//												null, null) + "/" : messageSource()
//								.getMessage("garmentStyleImageLocation", null,
//										null));
		
//		registry.addResourceHandler("/choriGarmentStyleImage/**")
//		.addResourceLocations("file:/"
//						+ messageSource()
//								.getMessage(
//										"UPLOAD_FILE",
//										null, null) + "/");
		
		registry.addResourceHandler("/choriGarmentStyleImage/**")
		.addResourceLocations(
				messageSource().getMessage("UPLOAD_FILE",
						null, null).contains(":") ? "file:/"
						+ messageSource()
								.getMessage(
										"UPLOAD_FILE",
										null, null) + "/" : messageSource()
						.getMessage("UPLOAD_FILE", null,
								null));
		
		// chori accessory image
//		registry.addResourceHandler("/choriAccessoryImage/**")
//				.addResourceLocations(
//						messageSource().getMessage("accessoryImageLocation",
//								null, null).contains(":") ? "file:/"
//								+ messageSource()
//										.getMessage(
//												"accessoryImageLocation",
//												null, null)+"/"
//								: messageSource().getMessage(
//										"accessoryImageLocation", null, null));
		
		registry.addResourceHandler("/choriAccessoryImage/**")
		.addResourceLocations(
//				"file:/"
//						+ 
						messageSource()
								.getMessage(
										"UPLOAD_FILE",
										null, null));

		// chori signature image
//		registry.addResourceHandler("/choriSignatureImage/**")
//				.addResourceLocations(
//						messageSource().getMessage("signatureImageLocation",
//								null, null).contains(":") ? "file:/"
//								+ messageSource().getMessage(
//										"signatureImageLocation", null, null)
//								: messageSource().getMessage(
//										"signatureImageLocation", null, null));
		
		registry.addResourceHandler("/choriSignatureImage/**")
		.addResourceLocations(messageSource()
								.getMessage(
										"UPLOAD_FILE",
										null, null));

		// chori packing guide
		registry.addResourceHandler("/choriPackingGuide/**")
				.addResourceLocations(
						messageSource().getMessage("packingGuideLocation",
								null, null).contains(":") ? "file:/"
								+ messageSource().getMessage(
										"packingGuideLocation", null, null)
								: messageSource().getMessage(
										"packingGuideLocation", null, null));

		registry.addResourceHandler("/choriPiattachFile/**")
				.addResourceLocations(
						messageSource().getMessage("piattachFileLocation",
								null, null).contains(":") ? "file:/"
								+ messageSource().getMessage(
										"piattachFileLocation", null, null)
								: messageSource().getMessage(
										"piattachFileLocation", null, null));

		registry.addResourceHandler("/choriManufactureGuide/**")
				.addResourceLocations(
						messageSource().getMessage("manufactureGuideLocation",
								null, null).contains(":") ? "file:/"
								+ messageSource().getMessage(
										"manufactureGuideLocation", null, null)
								: messageSource().getMessage(
										"manufactureGuideLocation", null, null));

		// Fabric information part
//		registry.addResourceHandler("/fabricInformationImage/**")
//				.addResourceLocations(
//						messageSource().getMessage("fabricInformationLocation",
//								null, null).contains(":") ? "file:/"
//								+ messageSource()
//										.getMessage(
//												"fabricInformationLocation",
//												null, null) : messageSource()
//								.getMessage("fabricInformationLocation", null,
//										null));
		
		registry.addResourceHandler("/fabricInformationImage/**")
		.addResourceLocations(
				"file:/"
						+ messageSource()
								.getMessage(
										"UPLOAD_FILE",
										null, null)+"/");

		// Fabric information detail part
//		registry.addResourceHandler("/fabricInformationDetailImage/**")
//				.addResourceLocations(
//						messageSource().getMessage(
//								"fabricInformationDetailLocation", null, null)
//								.contains(":") ? "file:/"
//								+ messageSource().getMessage(
//										"fabricInformationDetailLocation",
//										null, null) : messageSource()
//								.getMessage("fabricInformationDetailLocation",
//										null, null));
		
		registry.addResourceHandler("/fabricInformationDetailImage/**")
		.addResourceLocations("file:/"
						+ messageSource().getMessage(
								"UPLOAD_FILE",
								null, null)+"/");
	}

	// add new
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		final String languageFolder = "languages";
		final String configurationFolder = "configurations";
		String[] basenames = { configurationFolder + "/messages",
				languageFolder + "/language", configurationFolder + "/upload",
				languageFolder + "/accessory", languageFolder + "/customer",
				languageFolder + "/agent", languageFolder + "/accessoryprice",
				languageFolder + "/signature",
				languageFolder + "/accessorysupplier",
				languageFolder + "/color", languageFolder + "/ctnrtype",
				languageFolder + "/currency",
				languageFolder + "/currencyexchange",
				languageFolder + "/destination",
				languageFolder + "/garmentkind", languageFolder + "/sizegroup",
				languageFolder + "/unit", languageFolder + "/width",
				languageFolder + "/assignExternalAccessoryForPi",
				languageFolder + "/assignfabric", languageFolder + "/factory",
				languageFolder + "/fabricSupplier",
				languageFolder + "/garmentStyle",
				languageFolder + "/loginLogout", languageFolder + "/role",
				languageFolder + "/user",
				languageFolder + "/accessoryConsumption",
				languageFolder + "/shippingLine",
				languageFolder + "/garmentConsumption",
				languageFolder + "/orderinternalaccessory",
				languageFolder + "/size", languageFolder + "/accessorygroup",
				languageFolder + "/estimatetime" ,
				languageFolder + "/packingguide",
				languageFolder + "/orderexternal",
				languageFolder + "/rfpi",
				languageFolder + "/fpi",
				languageFolder + "/pi",
				languageFolder + "/orderaccsheet"};

		messageSource.setBasenames(basenames);
		return messageSource;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver ss = new SessionLocaleResolver();
		ss.setDefaultLocale(new Locale("vi"));
		return ss;
	}

	/*
	 * Bean validator for Controller
	 */
	@Bean
	public AccessoryConsumptionValidator accessoryConsumptionValidator() {
		AccessoryConsumptionValidator accessoryConsumptionValidator = new AccessoryConsumptionValidator();
		return accessoryConsumptionValidator;
	}

	@Bean
	public ShippingLineValidator shippingLineValidator() {
		ShippingLineValidator shippingLineValidator = new ShippingLineValidator();
		return shippingLineValidator;
	}

	@Bean
	public ShippingLineContactValidator shippingLineContactValidator() {
		ShippingLineContactValidator shippingLineContactValidator = new ShippingLineContactValidator();
		return shippingLineContactValidator;
	}

	@Bean
	public FactoryValidator factoryValidator() {
		return new FactoryValidator();
	}

	@Bean
	public AccessoryValidator accessoryValidator() {
		return new AccessoryValidator();
	}

	@Bean
	public CustomerValidator customerValidator() {
		CustomerValidator customerValidator = new CustomerValidator();
		return customerValidator;
	}

	@Bean
	public CustomerContactValidator customercontactValidator() {
		CustomerContactValidator customercontactValidator = new CustomerContactValidator();
		return customercontactValidator;
	}

	@Bean
	public AgentValidator agentValidator() {
		AgentValidator agentValidator = new AgentValidator();
		return agentValidator;
	}

	@Bean
	public ColorValidator colorValidator() {
		ColorValidator colorValidator = new ColorValidator();
		return colorValidator;
	}

	@Bean
	public DestinationValidator destinationValidator() {
		DestinationValidator destinationValidator = new DestinationValidator();
		return destinationValidator;
	}

	@Bean
	public GarmentkindValidator garmentkindValidator() {
		GarmentkindValidator garmentkindValidator = new GarmentkindValidator();
		return garmentkindValidator;
	}

	@Bean
	public WidthValidator widthValidator() {
		WidthValidator widthValidator = new WidthValidator();
		return widthValidator;
	}

	@Bean
	public CtnrtypeValidator ctnrValidator() {
		CtnrtypeValidator ctnrValidator = new CtnrtypeValidator();
		return ctnrValidator;
	}

	@Bean
	public AccessorySupplierValidator accessorysupplierValidator() {
		AccessorySupplierValidator accessorysupplierValidator = new AccessorySupplierValidator();
		return accessorysupplierValidator;
	}

	@Bean
	public AccessorySupplierContactValidator accessorysuppliercontactValidator() {
		AccessorySupplierContactValidator accessorysuppliercontactValidator = new AccessorySupplierContactValidator();
		return accessorysuppliercontactValidator;
	}

	@Bean
	public UnitValidator unitValidator() {
		return new UnitValidator();
	}

	@Bean
	public EstimatetimeValidator estimatetimeValidator() {
		return new EstimatetimeValidator();
	}

	@Bean
	public TypeValidator typeValidator() {
		return new TypeValidator();
	}

	@Bean
	public AccessoryGroupValidator accessoryGroupValidator() {
		return new AccessoryGroupValidator();
	}

	@Bean
	public FabricsupplierValidator fabricsupplierValidator() {
		return new FabricsupplierValidator();
	}

	@Bean
	public UserValidator userValidator() {
		return new UserValidator();
	}

	@Bean
	public CurrencyexchangeValidator currencyexchangeValidator() {
		return new CurrencyexchangeValidator();
	}

	@Bean
	public GarmentstyleValidator garmentstyleValidator() {
		GarmentstyleValidator garmentstyleValidator = new GarmentstyleValidator();
		return garmentstyleValidator;
	}

	@Bean
	public GarmentstyleaccessorydetailValidator garmentstyleaccessorydetailValidator() {
		return new GarmentstyleaccessorydetailValidator();
	}

	@Bean
	public CurrencyValidator currencyValidator() {
		return new CurrencyValidator();
	}

	@Bean
	public AccessoryPriceValidator accessoryPriceValidator() {
		return new AccessoryPriceValidator();
	}

//	@Bean
//	public JavaMailSender getMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		// Using Gmail SMTP configuration.
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("bleachclone69@gmail.com");
//		mailSender.setPassword("huancuibap");
//
//		Properties javaMailProperties = new Properties();
//		javaMailProperties.put("mail.smtp.starttls.enable", "true");
//		javaMailProperties.put("mail.smtp.auth", "true");
//		javaMailProperties.put("mail.transport.protocol", "smtp");
//		javaMailProperties.put("mail.debug", "true");
//
//		mailSender.setJavaMailProperties(javaMailProperties);
//		return mailSender;
//	}
}
