
samples=( 10 50 100 200 500 1000 10000 )
for i in "${samples[@]}"
do
	echo 'Running CASE-2 for l '$i
	echo -e "2 2\nM t\nJ f\nB\nE" | mvn -q exec:java -Dexec.mainClass=com.execute.Launcher -Dexec.args='l '$i
	echo '****** END ******'
done