package com.pratice.apachestormexamples.wordcountexample.maintopology;

import com.pratice.apachestormexamples.wordcountexample.WordCountBolt;
import com.pratice.apachestormexamples.wordcountexample.WordSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class WordCountTopology {
	
	public static void main(String[] args) {
		//creating a new topology
		TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout( "wordcountspout", new WordSpout() );
        builder.setBolt( "wordcountprime", new WordCountBolt() )
                .shuffleGrouping("wordcountspout");
        //shufflegrouping shuffles the tuples sent


        //configuration
        Config conf = new Config();
        
        //running storm in local mode
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("wordcount", conf, builder.createTopology());
        //sleep for 10 seconds
        Utils.sleep(10000);
        //killing the topology by its name : wordcount
        cluster.killTopology("wordcount");
        cluster.shutdown();
	}

}
