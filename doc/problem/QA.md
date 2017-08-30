mongodb数据库添加用户
https://github.com/leanote/leanote/wiki/QA#%E5%A6%82%E4%BD%95%E7%BB%91%E5%AE%9A%E5%9F%9F%E5%90%8D

MyBatis启动时控制台无限输出日志--主要检查配置文件
http://blog.csdn.net/isea533/article/details/51277786

Q chrome:
Failed to decode downloaded font: http://localhost:8080/fonts/leanote-font3/leanote.ttf?-vcf23i
index:1 OTS parsing error: incorrect entrySelector for table directory
A:pom.xml
```	   <resource>
		        <directory>${project.basedir}/src/main/resources</directory>
		        <filtering>true</filtering>
		        <excludes>
		            <exclude>static/fonts/**</exclude>
		        </excludes>
		    </resource>
		
		    <resource>
		        <directory>${project.basedir}/src/main/resources</directory>
		        <filtering>false</filtering>
		        <includes>
		            <include>static/fonts/**</include>
		        </includes>
		    </resource>
```