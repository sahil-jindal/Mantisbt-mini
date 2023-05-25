package com.utility;

import java.util.HashMap;

public class IssueVariable{
	
	 public HashMap<String, Integer> prior = new HashMap<>();
	 public HashMap<String, Integer> repro = new HashMap<>();
	 public HashMap<String, Integer> catog = new HashMap<>();
	 public HashMap<String, Integer> sever = new HashMap<>();
	 public HashMap<String, Integer> status = new HashMap<>();
	 public HashMap<String, Integer> resolution = new HashMap<>();

	public IssueVariable()
	{
		prior.put("none", 10);
		prior.put("low", 20);
		prior.put("normal", 30);
		prior.put("high", 40);
		prior.put("urgent", 50);
		prior.put("immediate", 60);
		
		repro.put("always", 10);
		repro.put("sometimes", 30);
		repro.put("random", 50);
		repro.put("have not tried", 70);
		repro.put("unable to reproduce", 90);
		repro.put("N/A", 100);

		catog.put("[All Projects] General", 1);
		catog.put("[All Projects] Selenium", 2);
		
		sever.put("feature", 10);
		sever.put("trivial", 20);
		sever.put("text", 30);
		sever.put("tweak", 40);
		sever.put("minor", 50);
		sever.put("major", 60);
		sever.put("crash", 70);
		sever.put("block", 80);

		status.put("new", 10);
		status.put("feedback", 20);
		status.put("acknowledged", 30);
		status.put("confirmed", 40);
		status.put("assigned", 50);
		status.put("resolved", 80);
		status.put("closed", 90);
		
		resolution.put("open", 10);
		resolution.put("fixed", 20);
		resolution.put("reopened", 30);
		resolution.put("unable to reproduce", 40);
		resolution.put("not fixable", 50);
		resolution.put("duplicate", 60);
		resolution.put("no change required", 70);
		resolution.put("suspended", 80);
		resolution.put("won't fix", 90);
		
	}
	
	
	
}
