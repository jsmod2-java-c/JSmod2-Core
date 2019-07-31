#!/bin/bash

#Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
#It needs to rely on smod2 and proxy. jsmod2 is an open source
#free plugin that is released under the GNU license. Please read
#the GNU open source license before using the software. To understand
#the appropriateness, if infringement, will be handled in accordance
#with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>

execute(){
    while true
    do
        echo "安装依赖包"
        yum -y install zlib-devel bzip2-devel openssl-devel ncurses-devel sqlite-devel readline-devel tk-devel gdbm-devel db4-devel libpcap-devel xz-devel
        echo "安装pip"
        yum remove pip -y && cd /usr/local/ && tar -xf pip-18.0.tar.gz && cd pip-18.0/ && python setup.py install
        echo "安装python"
        cd /usr/local/ && tar -zxvf Python-3.6.5.tgz && mv Python-3.6.5 python3 && cd python3 && ./configure && make && make install
        mv /usr/bin/python /usr/bin/python.bak
        ln -s /usr/local/bin/python3 /usr/bin/python && ln -s /usr/local/python3/bin/pip3 /usr/bin/pip3
        whereis pip && /usr/local/bin/pip3.6 install --upgrade pip && /usr/local/bin/pip install paramiko
        sed -i "1c #!/usr/bin/python2.7" /usr/bin/yum
        sed -i "1c #!/usr/bin/python2.7" /usr/libexec/urlgrabber-ext-down
        sed -i "1c #!/usr/bin/python2.7" /usr/sbin/firewalld
      
        echo "全部安装完毕"
        exit
    done
}
execute
python start.py
