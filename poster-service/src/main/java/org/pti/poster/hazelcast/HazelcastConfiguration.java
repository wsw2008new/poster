package org.pti.poster.hazelcast;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class HazelcastConfiguration {

	private HazelcastInstance hazelcastInstance;

	@PostConstruct
	public void init() {
		Config hazelcastConfig = new Config();
		NetworkConfig networkConfig=new NetworkConfig();
		InterfacesConfig interfacesConfig=new InterfacesConfig();
		JoinConfig joinConfig=new JoinConfig();
		MulticastConfig multicastConfig=new MulticastConfig();
		TcpIpConfig tcpIpConfig=new TcpIpConfig();
		tcpIpConfig.setEnabled(true);
		multicastConfig.setEnabled(false);

		List<String> members=new ArrayList<>();
		members.add("127.0.0.1");

		tcpIpConfig.setMembers(members);

		joinConfig.setMulticastConfig(multicastConfig);
		joinConfig.setTcpIpConfig(tcpIpConfig);
		interfacesConfig.addInterface("127.0.0.1");
		interfacesConfig.setEnabled(true);
		networkConfig.setInterfaces(interfacesConfig);
		networkConfig.setJoin(joinConfig);
		hazelcastConfig.setNetworkConfig(networkConfig);
		hazelcastInstance = Hazelcast.newHazelcastInstance(hazelcastConfig);
	}

	public HazelcastInstance getHazelcastInstance() {
		return hazelcastInstance;
	}
}