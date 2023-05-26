package com.utility;

import java.util.Map;
import static java.util.Map.entry;

public class IssueVariable {
	
	 public Map<String, Integer> prior = Map.ofEntries(
		 entry("none", 10),
		 entry("low", 20),
		 entry("normal", 30),
		 entry("high", 40),
		 entry("urgent", 50),
		 entry("immediate", 60)
	 );

	 public Map<String, Integer> repro = Map.ofEntries(
		 entry("always", 10),
		 entry("sometimes", 30),
		 entry("random", 50),
		 entry("have not tried", 70),
		 entry("unable to reproduce", 90),
		 entry("N/A", 100)
	 );

	 public Map<String, Integer> catog = Map.ofEntries(
		 entry("[All Projects] General", 1),
		 entry("[All Projects] Selenium", 2)
	 );

	 public Map<String, Integer> sever = Map.ofEntries(
		 entry("feature", 10),
		 entry("trivial", 20),
		 entry("text", 30),
		 entry("tweak", 40),
		 entry("minor", 50),
		 entry("major", 60),
		 entry("crash", 70),
		 entry("block", 80)
	 );

	 public Map<String, Integer> status = Map.ofEntries(
		 entry("new", 10),
		 entry("feedback", 20),
		 entry("acknowledged", 30),
		 entry("confirmed", 40),
		 entry("assigned", 50),
		 entry("resolved", 80),
		 entry("closed", 90)
	 );

	 public Map<String, Integer> resolution = Map.ofEntries(
		 entry("open", 10),
		 entry("fixed", 20),
		 entry("reopened", 30),
		 entry("unable to reproduce", 40),
		 entry("not fixable", 50),
		 entry("duplicate", 60),
		 entry("no change required", 70),
		 entry("suspended", 80),
		 entry("won't fix", 90)
	 );

	public IssueVariable() {}
}
