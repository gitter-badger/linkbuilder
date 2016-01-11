package org.halhelper.linkbuilder.methodtemplate;

import com.damnhandy.uri.template.UriTemplate;
import org.easymock.*;
import org.halhelper.linkbuilder.argumentresolver.ArgumentResolver;
import org.halhelper.linkbuilder.argumentresolver.ArgumentResolvers;
import org.halhelper.linkbuilder.utils.UriTemplateAugmenter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.hateoas.core.MethodParameters;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by osvaldopina on 1/4/16.
 */
public class TemplateGeneratorTest  extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @TestSubject
    TemplateGenerator templateGenerator = new TemplateGenerator();

    @Mock
    private TemplatePathDiscover templatePathDiscover;

    @Mock
    private UriTemplateAugmenter.Factory uriTemplateAugmenterFactory;

    @Mock
    private UriTemplateAugmenter uriTemplateAugmenter;

    private Method method;

    @Mock
    private TemplateGenerator.MethodParametersFactory methodParametersFactory;

    @Mock
    private ArgumentResolvers argumentResolvers;

    @Mock
    private ArgumentResolver argumentResolver;

    @Mock
    private MethodParameters methodParameters;

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private UriTemplate uriTemplate;

    @Before
    public void setUp() throws Exception {
        method = Request.class.getMethod("method", String.class);
    }

    @Test
    public void generate() {
        EasyMock.expect(uriTemplateAugmenterFactory.create()).andReturn(uriTemplateAugmenter);
        templatePathDiscover.augmentPath(uriTemplateAugmenter, method);
        EasyMock.expectLastCall();
        EasyMock.expect(methodParametersFactory.create(method)).andReturn(methodParameters);
        EasyMock.expect(methodParameters.getParameters()).andReturn(Arrays.asList(methodParameter));
        EasyMock.expect(argumentResolvers.getArgumentResolverFor(methodParameter)).andReturn(argumentResolver);
        argumentResolver.augmentTemplate(uriTemplateAugmenter, methodParameter);
        EasyMock.expectLastCall();
        EasyMock.expect(uriTemplateAugmenter.getUriTemplate()).andReturn(uriTemplate);

        replayAll();

        assertSame(uriTemplate, templateGenerator.generate(method, argumentResolvers));

        verifyAll();

    }


    public static class Request {

        public void method(@RequestParam("query1") String method) {

        }

    }




}