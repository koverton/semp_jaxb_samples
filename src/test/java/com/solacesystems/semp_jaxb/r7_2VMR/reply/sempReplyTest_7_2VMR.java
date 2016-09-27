package com.solacesystems.semp_jaxb.r7_2VMR.reply;

/*  This class is designed to test the JAXB capability to serialize SEMP
 *  from an object model that is derived from the SEMP schema by auto parsing
 *  the .xsd files for SEMP request and response
 */

import org.junit.Test;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class sempReplyTest_7_2VMR {
    private static String SEMP_VERSION = "r7_2VMR";

    private static RpcReply unmarshalRpcReply(String filename) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(RpcReply.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (RpcReply) unmarshaller.unmarshal(new File("resources/" + SEMP_VERSION + "/" + filename));
    }

    @Test
    public void showVersion() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-version.xml");
        RpcReply.Rpc.Show.Version version = reply.getRpc().getShow().getVersion();
        assertEquals("soltr_7.1.0.1602", version.getCurrentLoad());
        assertEquals("soltr_7.0.0.1078", version.getBackoutLoad());
        assertEquals(18, version.getLoads().getLoad().size());
        assertEquals("soltr_4.4.12", version.getLoads().getLoad().get(0).getVersion());
        assertEquals("soltr_7.1.0.1602", version.getLoads().getLoad().get(17).getVersion());
    }

    @Test
    public void getStatusSuccess() throws Exception {
        RpcReply reply = unmarshalRpcReply("set-success.xml");
        assertEquals("ok", reply.getExecuteResult().getCode());
    }

    @Test
    public void getStatusFailNotAllowed() throws Exception {
        RpcReply reply = unmarshalRpcReply("set-failure.not.allowed.xml");
        assertEquals("fail", reply.getExecuteResult().getCode());
        assertEquals("not allowed", reply.getExecuteResult().getReason());
        assertEquals(89, reply.getExecuteResult().getReasonCode().intValue());
    }

    @Test
    public void getStatusFailBadXml() throws Exception {
        RpcReply reply = unmarshalRpcReply("set-failure.parse.error.xml");
        assertEquals("invalid message: schema validation error", reply.getParseError());
    }

    @Test
    public void showClientStats() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-stats.client.detail.xml");
        SolStatsType stats = reply.getRpc()
                .getShow()
                .getStats()
                .getClient()
                .getGlobal()
                .getStats();
        assertEquals(515, stats.getTotalClients().intValue());
        assertEquals(16, stats.getCurrentIngressRatePerSecond());
        assertEquals(3874, stats.getCurrentIngressByteRatePerSecond().intValue());
        assertEquals(348257, stats.getZipStats().getIngressCompressedBytes());
        assertEquals(34198, stats.getIngressDiscards().getTotalIngressDiscards());
        assertEquals(2327, stats.getEgressDiscards().getTotalEgressDiscards());
        assertEquals(5432, stats.getRestConnectionStatistics().getHttpPostRequestMessagesReceived());
    }

    @Test
    public void showMessageSpool() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-message-spool.detail.xml");
        RpcReply.Rpc.Show.MessageSpool.MessageSpoolInfo info =
                reply.getRpc().getShow().getMessageSpool().getMessageSpoolInfo();

        assertEquals("Enabled (Primary)", info.getConfigStatus());
        assertEquals(60000, info.getMaxDiskUsage().intValue());
        assertTrue(info.isDatapathUp().booleanValue());
        assertEquals("AD-Active", info.getOperationalStatus());
    }

    @Test
    public void showQueueDetail() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-queue.detail.xml");
        assertEquals(14, reply.getRpc().getShow().getQueue().getQueues().getQueue().size());
        QueueType queue = reply.getRpc().getShow().getQueue().getQueues().getQueue().get(2);
        assertEquals("q_trade", queue.getName());
        assertEquals(4000, queue.getInfo().getQuota().intValue());
        assertTrue(queue.getInfo().isDurable());
        assertEquals("exclusive", queue.getInfo().getAccessType());
    }

    @Test
    public void showClientConnections() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-client.connections.xml");

        RpcReply.Rpc.Show.Client.PrimaryVirtualRouter clients = (RpcReply.Rpc.Show.Client.PrimaryVirtualRouter)
                reply.getRpc().getShow().getClient()
                        .getPrimaryVirtualRouterOrBackupVirtualRouterOrInternalVirtualRouter()
                        .get(0);
        assertEquals(11, clients.getClient().size());
        ClientType lastClient = clients.getClient().get(10);
        assertEquals("solcache-demo/3293/#00010001", lastClient.getName());
        assertEquals(262144, lastClient.getConnection().get(0).getAdvertisedWindowSize().intValue());
        assertEquals(0, lastClient.getConnection().get(0).getFastRetransmits().intValue());
    }

    @Test
    public void showQueueStats() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-queue.stats.detail.xml");

        MessageSpoolStatsType stats = reply.getRpc().getShow()
                .getQueue()
                .getQueues()
                .getQueue().get(0)
                .getStats()
                .getMessageSpoolStats();
        assertEquals(BigInteger.valueOf(10), stats.getTotalMessagesSpooled());

        EgressFlowStatsType flowStats = stats.getEgressFlowStats().get(0);
        assertEquals("Deliver from input stream", flowStats.getFlowState());
    }

    @Test
    public void showAclProfileDetail() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-acl-profile.detail.xml");

        RpcReply.Rpc.Show.AclProfile.AclProfiles.AclProfile2 profile = reply
                .getRpc().getShow()
                .getAclProfile()
                .getAclProfiles()
                .getAclProfile().get(0);

        assertEquals("deleteme", profile.getProfileName());
        assertEquals(2, profile.getClientConnect().getNumExceptions());
        assertEquals("195.194.193.192/24", profile.getClientConnect()
                .getExceptions()
                .getException().get(0)
                .getValue());

        assertEquals(2, profile.getPublishTopic().getNumExceptions());
        assertEquals("pub/topic/lock2", profile.getPublishTopic()
                .getExceptions()
                .getException().get(1)
                .getValue());
    }

    @Test
    public void showClientUsernameDetail()throws Exception {
        RpcReply reply = unmarshalRpcReply("show-client-username.detail.xml");

        ClientUsernameType username = reply.getRpc().getShow()
                .getClientUsername()
                .getClientUsernames()
                .getClientUsername().get(0);

        assertEquals("deleteme", username.getClientUsername());
        assertEquals("temp", username.getMessageVpn());
        assertEquals(16000L, username.getMaxEndpoints().longValue());
    }

    @Test
    public void showMessageVpnDetail() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-message-vpn.detail.xml");

        RpcReply.Rpc.Show.MessageVpn.Vpn vpn =
                reply.getRpc().getShow()
                .getMessageVpn().getVpn().get(0);

        assertEquals("test1", vpn.getName());
        assertEquals(true, vpn.isEnabled());
        assertEquals("Up", vpn.getLocalStatus());
    }

    @Test
    public void showBridgeDetail() throws Exception {
        RpcReply reply = unmarshalRpcReply("show-bridge.detail.xml");
        RpcReply.Rpc.Show.Bridge.Bridges.Bridge2 bridge = reply.getRpc().getShow()
                .getBridge().getBridges().getBridge().get(0);

        assertEquals("AcmeBridge", bridge.getBridgeName());
        assertEquals("Disabled", bridge.getAdminState());
        assertEquals(0, bridge.getConnectionUptimeInSeconds().intValue());
    }
}