package com.example.grp.codechallenge.producer.domain;

import java.util.List;

public class ProducerResult {

	private List<PrizeInterval> min;
	private List<PrizeInterval> max;

	public List<PrizeInterval> getMin() {
		return min;
	}

	public void setMin(List<PrizeInterval> min) {
		this.min = min;
	}

	public List<PrizeInterval> getMax() {
		return max;
	}

	public void setMax(List<PrizeInterval> max) {
		this.max = max;
	}

}
