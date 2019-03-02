package io.jmlim.springdatajpastudy;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class JmlimRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        // 여기서부터 ==========================================================================
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(Jmlim.class);
        beanDefinition.getPropertyValues().add("name", "임정묵");

        // ========= 여기까지 사이의 일어난 일이 SpringDataJpa 같은 경우는 매우 복잡하다고 생각하면 됨.

        //Jmlim 이란 빈을 등록함.
        beanDefinitionRegistry.registerBeanDefinition("jmlim", beanDefinition);
    }
}
