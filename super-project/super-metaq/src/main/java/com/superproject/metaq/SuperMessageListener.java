package	com.superproject.metaq;

import java.util.concurrent.Executor;

import org.apache.log4j.Logger;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;
 

public class SuperMessageListener extends DefaultMessageListener<String> {
	private Logger logger = Logger.getLogger(SuperMessageListener.class);
	//@Resource
	//private AreaCodeService areaCodeService;
	@Override
	public void onReceiveMessages(MetaqMessage<String> arg0) {
		logger.info("=====receive trade message:" + arg0.getBody());
	}
	@Override
	public void recieveMessages(Message mess) throws InterruptedException {
		System.out.println("=============================TOPIC:"+mess.getTopic());
//		System.out.println("=============================ID:"+mess.getId());
//		System.out.println("=============================DATA:"+String.valueOf(mess.getData()));
//		if(Topic.SUPER_TOPIC.equals(mess.getTopic())){
//			MsgData data = (MsgData) SerializeUtil.unserialize(mess.getData());
//			logger.info("=====SUPER_TOPIC data:"+data.toString());
//		}
//		if(Topic.ONE_TO_ONE.equals(mess.getTopic())){
//			MsgData data = (MsgData) SerializeUtil.unserialize(mess.getData());
//			logger.info("=====ONE_TO_ONE data:"+data.toString());
//		}
//		if(Topic.ONE_TO_MANY.equals(mess.getTopic())){
//			MsgData data = (MsgData) SerializeUtil.unserialize(mess.getData());
//			logger.info("=====ONE_TO_MANY data:"+data.toString());
//		}
	}
	public static void main(String[] args) throws Exception {
        final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZKConfig zkConfig = new ZKConfig();
        //设置zookeeper地址
        zkConfig.zkConnect = "120.25.152.126:2181";
        metaClientConfig.setZkConfig(zkConfig);
        // New session factory,强烈建议使用单例
        MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        // subscribed topic
        final String topic = "super-topic";
        // consumer group
        final String group = "super-topic-client";
        // create consumer,强烈建议使用单例
        MessageConsumer consumer = sessionFactory.createConsumer(new ConsumerConfig(group));
        // subscribe topic
        consumer.subscribe(topic, 1024 * 1024, new MessageListener() {
            /**
             * 接收到消息列表，只有message不为空并且不为null的情况下会触发此方法
             */ 
            public void recieveMessages(Message message) {
                System.out.println("Receive message " + new String(message.getData()));
            }

			public Executor getExecutor() {
				// TODO Auto-generated method stub
				return null;
			}
        });
        // complete subscribe
        consumer.completeSubscribe();
    }

   
   
}