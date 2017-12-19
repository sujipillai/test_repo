package com.pratice.apachestormexamples.wordcountexample;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.utils.Utils;

public class WordCountBolt extends BaseRichBolt{

	private OutputCollector collector;

	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector=collector;
	}
	
	public void execute(Tuple tuple) {
		//sleep for 1 second
		Utils.sleep(1000);
		//getting sentence from spout
		String paragraph=tuple.getString(0);
		System.out.print("\nparagraph= "+paragraph);

		//processing the data received from spout 
		StringBuilder sb=new StringBuilder();
		//to remove extra spaces if present in the sentence
		for(String s: paragraph.split(" ")){

			if(!s.equals(""))        // if String s is not a space
				sb.append(s+" ");    // add a single space

		}
		String[] words= sb.toString().split(" ");
		System.out.println(" ; word count= "+words.length);
		
		//acknowledging the received tuple
		collector.ack(tuple);
	}


	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("paragraph"));
	}

}
