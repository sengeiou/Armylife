package cn.armylife.payments.utils;import cn.armylife.payments.domain.WeChatUsers;import com.github.wxpay.sdk.WXPayConfig;import org.apache.commons.io.IOUtils;import org.springframework.stereotype.Component;import java.io.ByteArrayInputStream;import java.io.InputStream;@Componentpublic class MemberWXMyConfigUtil implements WXPayConfig {        private byte[] certData;        WeChatUsers weChatUsers;//        public MemberWXMyConfigUtil() throws Exception {//            Resource resource = new ClassPathResource("/apiclient_cert.p12");//"apiclient_cert.p12";//从微信商户平台下载的安全证书存放的目录//          // String certPath="E:\\javaPicture\\yezz\\apiclient_cert.p12";//            File file = resource.getFile();//            InputStream certStream = new FileInputStream(file);//            this.certData = new byte[(int) file.length()];//            certStream.read(this.certData);//            certStream.close();//        }        @Override        public String getAppID() {            return weChatUsers.getAppId();        }        //parnerid        @Override        public String getMchID() {            return weChatUsers.getMCHID();        }        @Override        public String getKey() {            return "STFpefEPYK5smME0vleQKoywvo4vTGiG";// weChatUsers.getKEY();        }        @Override        public InputStream getCertStream() {            try {                InputStream certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("apiclient_cert.p12");                this.certData = IOUtils.toByteArray(certStream);                certStream.close();            } catch (Exception e) {                e.printStackTrace();            }            ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);            return certBis;        }        @Override        public int getHttpConnectTimeoutMs() {            return 8000;        }        @Override        public int getHttpReadTimeoutMs() {            return 10000;        }    }