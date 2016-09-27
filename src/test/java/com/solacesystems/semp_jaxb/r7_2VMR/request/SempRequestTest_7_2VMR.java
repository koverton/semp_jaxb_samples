package com.solacesystems.semp_jaxb.r7_2VMR.request;

import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import static org.junit.Assert.*;

public class SempRequestTest_7_2VMR {
    private static final String SEMP_VERSION = "soltr/7_1";
    private StringWriter w;
    private StringWriter trimmed;
    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;
    private StringBuffer buf;
    private ObjectFactory myFactory;

    private String generateXml(Rpc rpc){
        String result = null;
        try {
            // output pretty printed
            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(rpc, w);
            int index = w.toString().indexOf("<rpc");
            trimmed.write(w.toString(), index, w.toString().length() - index);
            //Move to a new line
            System.out.println(trimmed);
            result = trimmed.toString();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        buf = w.getBuffer();
        buf.setLength(0);
        buf = trimmed.getBuffer();
        buf.setLength(0);
        return result;
    }

    @Before
    public void setup() {
        try {
            w = new StringWriter();
            trimmed = new StringWriter();
            jaxbContext = JAXBContext.newInstance(Rpc.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            myFactory = new ObjectFactory();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showVersion() {
        // Show the appliance version
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setShow(myFactory.createRpcShow());
        myrpc.getShow().setVersion(myFactory.createKeywordType());
        String xml = generateXml(myrpc);
        assertEquals("<rpc semp-version=\"" + SEMP_VERSION + "\"><show><version/></show></rpc>", xml);
    }

    @Test
    public void showClientStats() {
        // Show the appliance version
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setShow(myFactory.createRpcShow());
        myrpc.getShow().setStats(myFactory.createRpcShowStats());
        myrpc.getShow().getStats().setClient(myFactory.createRpcShowStatsClient());
        myrpc.getShow().getStats().getClient().setDetail(myFactory.createKeywordType());
        String xml = generateXml(myrpc);
        assertEquals("<rpc semp-version=\"" + SEMP_VERSION + "\"><show><stats><client><detail/></client></stats></show></rpc>", xml);
    }

    @Test
    public void showMessageSpool() {
        // Show the appliance version
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setShow(myFactory.createRpcShow());
        myrpc.getShow().setMessageSpool(myFactory.createRpcShowMessageSpool());
        myrpc.getShow().getMessageSpool().setDetail(myFactory.createKeywordType());
        String xml = generateXml(myrpc);
        assertEquals("<rpc semp-version=\"" + SEMP_VERSION + "\"><show><message-spool><detail/></message-spool></show></rpc>", xml);
    }

    @Test
    public void showQueueDetail() {
        // Show the appliance version
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setShow(myFactory.createRpcShow());
        myrpc.getShow().setQueue(myFactory.createRpcShowQueue());
        myrpc.getShow().getQueue().setName("foo");
        myrpc.getShow().getQueue().setVpnName("bar");
        myrpc.getShow().getQueue().setDetail(myFactory.createKeywordType());
        String xml = generateXml(myrpc);
        assertEquals("<rpc semp-version=\"" + SEMP_VERSION + "\"><show><queue><name>foo</name><vpn-name>bar</vpn-name><detail/></queue></show></rpc>", xml);
    }

    @Test
    public void showClientConnections() {
        // Show the appliance version
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setShow(myFactory.createRpcShow());
        myrpc.getShow().setClient(myFactory.createRpcShowClient());
        myrpc.getShow().getClient().setName("foo");
        myrpc.getShow().getClient().setVpnName("bar");
        myrpc.getShow().getClient().setConnections(myFactory.createKeywordType());
        String xml = generateXml(myrpc);
        assertEquals("<rpc semp-version=\"" + SEMP_VERSION + "\"><show><client><name>foo</name><vpn-name>bar</vpn-name><connections/></client></show></rpc>", xml);
    }

    @Test
    public void createMessageVpn() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setCreate(myFactory.createRpcCreate());
        myrpc.getCreate().setMessageVpn(myFactory.createRpcCreateMessageVpn());
        myrpc.getCreate().getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
    }

    @Test
    public void setConnectionsMaxPerVpn() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageVpn(myFactory.createRpcMessageVpn());
        myrpc.getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getMessageVpn().setMaxConnections(myFactory.createRpcMessageVpnMaxConnections());
        myrpc.getMessageVpn().getMaxConnections().setValue(1000);
        String xml = generateXml(myrpc);
        System.err.println(xml);
    }

    @Test
    public void setSubscriptionsMaxPerVpn() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageVpn(myFactory.createRpcMessageVpn());
        myrpc.getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getMessageVpn().setMaxSubscriptions(myFactory.createRpcMessageVpnMaxSubscriptions());
        myrpc.getMessageVpn().getMaxSubscriptions().setValue(333333);
    }

    @Test
    public void setClientCertificateAuthentication() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageVpn(myFactory.createRpcMessageVpn());
        myrpc.getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getMessageVpn().setAuthentication(myFactory.createRpcMessageVpnAuthentication());
        myrpc.getMessageVpn().getAuthentication().setUserClass(myFactory.createRpcMessageVpnAuthenticationUserClass());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setClient(myFactory.createKeywordType());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setBasic(myFactory.createRpcMessageVpnAuthenticationUserClassBasic());
        myrpc.getMessageVpn().getAuthentication().getUserClass().getBasic().setShutdown(myFactory.createKeywordType());

        String xml = generateXml(myrpc);
    }

    @Test
    public void enableVpn() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageVpn(myFactory.createRpcMessageVpn());
        myrpc.getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getMessageVpn().setNo(myFactory.createRpcMessageVpnNo());
        myrpc.getMessageVpn().getNo().setShutdown(new Rpc.MessageVpn.No.Shutdown());

        String xml = generateXml(myrpc);
        assertEquals("<rpc semp-version=\"" + SEMP_VERSION + "\"><message-vpn><vpn-name>TestVPNCreatedByJAXB</vpn-name><no><shutdown/></no></message-vpn></rpc>", xml);
    }

    @Test
    public void setCertificatesNoKerberos() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageVpn(myFactory.createRpcMessageVpn());
        myrpc.getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getMessageVpn().setAuthentication(myFactory.createRpcMessageVpnAuthentication());
        myrpc.getMessageVpn().getAuthentication().setUserClass(myFactory.createRpcMessageVpnAuthenticationUserClass());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setClient(myFactory.createKeywordType());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setKerberos(myFactory.createRpcMessageVpnAuthenticationUserClassKerberos());
        myrpc.getMessageVpn().getAuthentication().getUserClass().getKerberos().setShutdown(myFactory.createKeywordType());
    }

    @Test
    public void setCertificateChainDepth() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageVpn(myFactory.createRpcMessageVpn());
        myrpc.getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getMessageVpn().setAuthentication(myFactory.createRpcMessageVpnAuthentication());
        myrpc.getMessageVpn().getAuthentication().setUserClass(myFactory.createRpcMessageVpnAuthenticationUserClass());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setClient(myFactory.createKeywordType());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setClientCertificate(myFactory.createRpcMessageVpnAuthenticationUserClassClientCertificate());
        myrpc.getMessageVpn().getAuthentication().getUserClass().getClientCertificate().setMaxCertificateChainDepth(myFactory.createRpcMessageVpnAuthenticationUserClassClientCertificateMaxCertificateChainDepth());
        myrpc.getMessageVpn().getAuthentication().getUserClass().getClientCertificate().getMaxCertificateChainDepth().setMaxDepth(3);
    }

    @Test
    public void setCertificateAuthentication() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageVpn(myFactory.createRpcMessageVpn());
        myrpc.getMessageVpn().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getMessageVpn().setAuthentication(myFactory.createRpcMessageVpnAuthentication());
        myrpc.getMessageVpn().getAuthentication().setUserClass(myFactory.createRpcMessageVpnAuthenticationUserClass());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setClient(myFactory.createKeywordType());
        myrpc.getMessageVpn().getAuthentication().getUserClass().setClientCertificate(myFactory.createRpcMessageVpnAuthenticationUserClassClientCertificate());
        myrpc.getMessageVpn().getAuthentication().getUserClass().getClientCertificate().setNo(myFactory.createRpcMessageVpnAuthenticationUserClassClientCertificateNo());
        myrpc.getMessageVpn().getAuthentication().getUserClass().getClientCertificate().getNo().setShutdown(new Rpc.MessageVpn.Authentication.UserClass.ClientCertificate.No.Shutdown());

    }

    @Test
    public void createDurableQueue() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageSpool(myFactory.createRpcMessageSpool());
        Rpc.MessageSpool spool = myrpc.getMessageSpool();
        spool.setVpnName("TestVPNCreatedByJAXB");
        spool.setCreate(myFactory.createRpcMessageSpoolCreate());
        spool.getCreate().setQueue(myFactory.createRpcMessageSpoolCreateQueue());
        Rpc.MessageSpool.Create.Queue queue = spool.getCreate().getQueue();
        queue.setName("test_queue_name");
    }

    @Test
    public void setDurableQueueAccessType() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageSpool(myFactory.createRpcMessageSpool());
        Rpc.MessageSpool spool = myrpc.getMessageSpool();
        spool.setVpnName("TestVPNCreatedByJAXB");
        spool.setQueue(myFactory.createRpcMessageSpoolQueue());
        Rpc.MessageSpool.Queue queue = spool.getQueue();
        queue.setName("test_queue_name");
        queue.setAccessType(myFactory.createRpcMessageSpoolQueueAccessType());
        queue.getAccessType().setExclusive(myFactory.createKeywordType());
    }

    @Test
    public void setDurableQueueMaxSpoolUsage() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setMessageSpool(myFactory.createRpcMessageSpool());
        Rpc.MessageSpool spool = myrpc.getMessageSpool();
        spool.setVpnName("TestVPNCreatedByJAXB");
        spool.setQueue(myFactory.createRpcMessageSpoolQueue());
        Rpc.MessageSpool.Queue queue = spool.getQueue();
        queue.setName("test_queue_name");
        queue.setMaxSpoolUsage(myFactory.createRpcMessageSpoolQueueMaxSpoolUsage());
        queue.getMaxSpoolUsage().setSize(333);
    }

    @Test
    public void createClientProfile() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setCreate(myFactory.createRpcCreate());
        myrpc.getCreate().setClientProfile(myFactory.createRpcCreateClientProfile());
        myrpc.getCreate().getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getCreate().getClientProfile().setVpnName("TestVPNCreatedByJAXB");
    }

    @Test
    public void setClientProfileADReceive() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setClientProfile(myFactory.createRpcClientProfile());
        myrpc.getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getClientProfile().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getClientProfile().setMessageSpool(myFactory.createRpcClientProfileMessageSpool());
        myrpc.getClientProfile().getMessageSpool().setAllowGuaranteedMessageReceive(myFactory.createKeywordType());

    }

    @Test
    public void setClientProfileADEndpointCreate() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setClientProfile(myFactory.createRpcClientProfile());
        myrpc.getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getClientProfile().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getClientProfile().setMessageSpool(myFactory.createRpcClientProfileMessageSpool());
        myrpc.getClientProfile().getMessageSpool().setAllowGuaranteedEndpointCreate(myFactory.createKeywordType());
    }

    @Test
    public void setClientProfileTXSessions() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setClientProfile(myFactory.createRpcClientProfile());
        myrpc.getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getClientProfile().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getClientProfile().setMessageSpool(myFactory.createRpcClientProfileMessageSpool());
        myrpc.getClientProfile().getMessageSpool().setAllowTransactedSessions(myFactory.createKeywordType());
    }

    @Test
    public void setClientProfileMaxConnectionsPerUsername() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setClientProfile(myFactory.createRpcClientProfile());
        myrpc.getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getClientProfile().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getClientProfile().setMessageSpool(myFactory.createRpcClientProfileMessageSpool());
        myrpc.getClientProfile().setMaxConnectionsPerClientUsername(myFactory.createRpcClientProfileMaxConnectionsPerClientUsername());
        myrpc.getClientProfile().getMaxConnectionsPerClientUsername().setValue(10);
    }

    @Test
    public void setClientProfileD1QueueMaxDepth() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setClientProfile(myFactory.createRpcClientProfile());
        myrpc.getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getClientProfile().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getClientProfile().setQueue(myFactory.createRpcClientProfileQueue());
        myrpc.getClientProfile().getQueue().setType(CLIENTPROFILEQUEUENAMEPARAM.D_1);
        myrpc.getClientProfile().getQueue().setMaxDepth(myFactory.createRpcClientProfileQueueMaxDepth());
        myrpc.getClientProfile().getQueue().getMaxDepth().setDepth(20000);
    }

    @Test
    public void setClientProfileD1QueueMinMsgBurst() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setClientProfile(myFactory.createRpcClientProfile());
        myrpc.getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getClientProfile().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getClientProfile().setQueue(myFactory.createRpcClientProfileQueue());
        myrpc.getClientProfile().getQueue().setType(CLIENTPROFILEQUEUENAMEPARAM.D_1);
        myrpc.getClientProfile().getQueue().setMinMsgBurst(myFactory.createRpcClientProfileQueueMinMsgBurst());
        myrpc.getClientProfile().getQueue().getMinMsgBurst().setDepth(10);
    }

    @Test
    public void setClientProfileTcpICW() {
        Rpc myrpc = new Rpc();
        myrpc.setSempVersion(SEMP_VERSION);
        myrpc.setClientProfile(myFactory.createRpcClientProfile());
        myrpc.getClientProfile().setName("JAXB1-ClientProfile");
        myrpc.getClientProfile().setVpnName("TestVPNCreatedByJAXB");
        myrpc.getClientProfile().setTcp(myFactory.createRpcClientProfileTcp());
        myrpc.getClientProfile().getTcp().setInitialCwnd(myFactory.createRpcClientProfileTcpInitialCwnd());
        myrpc.getClientProfile().getTcp().getInitialCwnd().setNumMss(1460);
    }
}
