<?php
	
	function getEventData($date) {
		echo '"events" : [';
		
		$result = mysql_query("select * from events where `date` >= date '".$date."'");
		$isFirstRecord = true;
		while($row = mysql_fetch_array($result)) {
			if(! $isFirstRecord) {
				echo ',';
			}
			echo '{';
			echo '"eventid" : "'.$row['eventid'].'",';
			echo '"eventname" : "'.$row['eventname'].'",';
			echo '"eventinfo" : "'.$row['eventinfo'].'",';
			echo '"eventfees" : "'.$row['eventfees'].'"';
			echo '}';
			$isFirstRecord = false;
		}
		
		echo ']';
	}
	
	function getContactData($date) {
		echo '"contacts" : [';
		
		$result = mysql_query("select * from contacts where `date` >= date '".$date."'");
		$isFirstRecord = true;
		while($row = mysql_fetch_array($result)) {
			if(! $isFirstRecord) {
				echo ',';
			}
			echo '{';
			echo '"committeeid" : "'.$row['committeeid'].'",';
			echo '"committeename" : "'.$row['committeename'].'",';
			echo '"post" : "'.$row['post'].'",';
			echo '"name" : "'.$row['name'].'",';
			echo '"mobno" : "'.$row['mobno'].'",';
			echo '"emailid" : "'.$row['emailid'].'"';
			echo '}';
			$isFirstRecord = false;
		}
		
		echo ']';
	}
	
	function getRuleData($date) {
		echo '"rules" : [';
		
		$result = mysql_query("select * from rules where `date` >= date '".$date."'");
		$isFirstRecord = true;
		while($row = mysql_fetch_array($result)) {
			if(! $isFirstRecord) {
				echo ',';
			}
			echo '{';
			echo '"eventid" : "'.$row['eventid'].'",';
			echo '"rule" : "'.$row['rule'].'"';
			echo '}';
			$isFirstRecord = false;
		}
		
		echo ']';
	}
	
	function getDownloadData($date) {
		echo '"downloads" : [';
		
		$result = mysql_query("select * from downloads where `date` >= date '".$date."'");
		$isFirstRecord = true;
		while($row = mysql_fetch_array($result)) {
			if(! $isFirstRecord) {
				echo ',';
			}
			echo '{';
			echo '"eventid" : "'.$row['eventid'].'",';
			echo '"description" : "'.$row['description'].'",';
			echo '"name" : "'.$row['name'].'",';
			echo '"url" : "'.$row['url'].'",';
			echo '}';
			$isFirstRecord = false;
		}
		
		echo ']';
	}
	
	function getDateData() {
		echo '"date" : "'.date("Y-m-d").'"';
	}
	
	include "connect.php";
	
	if(isset($_POST['lastdate'])) {
		$date = date($_POST['lastdate']);
	}
	else {
		$date = date('2015-12-30');
	}

	echo "{";
	getEventData($date);
	echo ',';
	getRuleData($date);
	echo ',';
	getDownloadData($date);
	echo ',';
	getContactData($date);
	echo ',';
	getDateData();
	echo "}";
	
?>