<?Php
echo "Maximum allowed file size: ".ini_get('upload_max_filesize');
echo "<br>";
echo "Maximum input time : ".ini_get('max_input_time');
echo "<br>";
echo "Maximum execution time : ".ini_get('max_execution_time');
echo "<br>";
echo "Maximum Post size : ".ini_get('post_max_size');
?>