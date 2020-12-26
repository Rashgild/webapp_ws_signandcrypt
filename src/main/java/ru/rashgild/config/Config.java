package ru.rashgild.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  /*  @Autowired
    private Injecter injecter;

    @Bean
    public HandlerResolver handlerResolver() {
        return new HandlerResolver() {
            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                List<Handler> handlerChain = new ArrayList<>();
                handlerChain.add(new Injecter());
                return handlerChain;
            }
        };
    }*/
}
