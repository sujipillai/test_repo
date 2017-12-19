package com.pratice.apachestormexamples.wordcountexample;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class WordSpout extends BaseRichSpout{

	private SpoutOutputCollector collector;
	
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		this.collector=collector;
	}
	
	public void nextTuple() {
		Utils.sleep(100);
		//array of strings of various sentences
		final String[] paragraphs = new String[] {"After creating the topology, "
				+ "the PrimeNumberTopology class creates the infrastructure to "
				+ "execute the topology in local mode.", 
				"To construct a Topology, create an instance of TopologyBuilder", 
				"Each spout and bolt has a name", 
				"Hello World", 
		"Apache"};
		final Random rand = new Random();
		//using random to randomly take any sentence from the string array
		final String paragraph = paragraphs[rand.nextInt(paragraphs.length)];
		collector.emit(new Values(paragraph));
	}


	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("paragraph"));
	}

}
