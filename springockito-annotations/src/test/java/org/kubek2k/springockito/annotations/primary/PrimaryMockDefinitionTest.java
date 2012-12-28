package org.kubek2k.springockito.annotations.primary;

import org.kubek2k.springockito.annotations.InnerBean;
import org.kubek2k.springockito.annotations.OuterBean;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;

@ContextConfiguration(loader = SpringockitoContextLoader.class,
        locations = "classpath:/mockPrimaryContext.xml")
public class PrimaryMockDefinitionTest extends AbstractTestNGSpringContextTests {

    @ReplaceWithMock
    @Autowired
    private InnerBean innerBean;

    @Autowired
    private OuterBean outerBean;

    @Test
    @DirtiesContext
    public void shouldUseMockInsteadOfOriginalBean() {
        outerBean.doSomething();

        verify(innerBean).doSomething();
    }

}