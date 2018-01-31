<?php

/* Database config */

$db_host		= 'localhost';
$db_user		= 'root';
$db_pass		= '';
$db_database	= 'prajwalan'; 

/* End config */

$link = mysql_connect($db_host,$db_user,$db_pass) or die('Unknown Error Occurred. Try again after some time.');

mysql_select_db($db_database,$link);
mysql_query("SET names UTF8");

?>