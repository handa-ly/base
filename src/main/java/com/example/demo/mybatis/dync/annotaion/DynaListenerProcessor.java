package com.example.demo.mybatis.dync.annotaion;/**
 * @Author handa
 * Description:
 * Date: Created in 20:55 2020/8/3
 * Modified By:
 */

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: hanDa
 * @Date: 2020/8/3 20:55
 * @Version:1.0
 * @Description:
 */
@Component
public class DynaListenerProcessor implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;
    private final ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    private final MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    private static final String FULLTEXT_SCAN_PACKAGE_PATH = "com.example.demo.pojo.quartz";

    public static Set<String> classNameSet;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    void init() throws IOException, ClassNotFoundException {
       classNameSet =  doScan(FULLTEXT_SCAN_PACKAGE_PATH);
    }

    /**
     * 利用spring提供的扫描包下面的类信息,再通过classfrom反射获得类信息
     *
     * @param scanPath
     * @return
     * @throws IOException
     */
    public Set<String> doScan(String scanPath) throws IOException {
        Set<String> classNameList = new HashSet<>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                .concat(ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(scanPath))
                        .concat("/**/*.class"));
        Resource[] resources = resolver.getResources(packageSearchPath);
        MetadataReader metadataReader = null;
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                metadataReader = metadataReaderFactory.getMetadataReader(resource);
                try {
                    if (metadataReader.getClassMetadata().isConcrete()) {
                        Class clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                        if(clazz.isAnnotationPresent(DynaEntity.class)){
                            classNameList.add(clazz.getName());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return classNameList;
    }
}