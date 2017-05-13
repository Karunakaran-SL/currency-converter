package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;

import com.converter.currency.demo.service.api.StatsService;
import com.converter.currency.demo.service.type.Stats;
import com.converter.currency.demo.web.StatsController;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsControllerTest {

	@Autowired
	StatsController controller;
	@Autowired
	private StatsService statsService;
	
	@Test
	public void testGetStats(){
		assertEquals("stats",controller.getStats(new ExtendedModelMap()));
		
		Map<String, AtomicInteger> map = controller.getStatsApi();
		assertEquals(0, map.size());
		
		statsService.incrementCount(Stats.TOTAL_CURRENCY_CALL.getValue());
		statsService.incrementCount(Stats.TOTAL_CURRENCY_CALL_ERROR.getValue());
		assertEquals("stats",controller.getStats(new ExtendedModelMap()));
		map = controller.getStatsApi();
		assertEquals(2, map.size());
	}

}
