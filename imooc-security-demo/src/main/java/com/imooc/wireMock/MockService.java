package com.imooc.wireMock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by 邓仁波 on 2017-11-1.
 */
public class MockService {
    public static void main(String[] args) throws IOException {
        WireMock.configureFor("123.207.157.149",466);
        WireMock.removeAllMappings();
        mock("/y","1.json");
    }

    private static void mock(String url,String file) throws IOException {
        ClassPathResource resource=new ClassPathResource("mock/response/"+file);
        String content= StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8").toArray(),"\n");
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
