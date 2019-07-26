#!/usr/bin/python3.7
# coding=utf-8
'''
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href='http://jsmod2.cn'>that<a>
这个python脚本可以用于下载java和scala相关库
并同时启动服务端
使用bat和shell脚本可以安装python的库
Created by MagicLu550 on 6.6,2019
:author fafa_junhe
'''
import os

import os
import platform
import subprocess
import sys

from requests import get
from os.path import expanduser
home = expanduser('~')

linuxurl ='https://dl.jymc.top/java/jre_x86.tar.gz'
linuxurl64 ='https://dl.jymc.top/java/jre_x64.tar.gz'
#采用我的下载服务器下载java
windowsurl ='http://dl.jymc.top/java/windows-i586.tar.gz'
windowsurl64 = 'http://dl.jymc.top/java/windows-x64.tar.gz'
#采用我的下载服务器下载java

class bcolors:
    CEND      = '\33[0m'
    CBOLD     = '\33[1m'
    CITALIC   = '\33[3m'
    CURL      = '\33[4m'
    CBLINK    = '\33[5m'
    CBLINK2   = '\33[6m'
    CSELECTED = '\33[7m'

    CBLACK  = '\33[30m'
    CRED    = '\33[31m'
    CGREEN  = '\33[32m'
    CYELLOW = '\33[33m'
    CBLUE   = '\33[34m'
    CVIOLET = '\33[35m'
    CBEIGE  = '\33[36m'
    CWHITE  = '\33[37m'

    CBLACKBG  = '\33[40m'
    CREDBG    = '\33[41m'
    CGREENBG  = '\33[42m'
    CYELLOWBG = '\33[43m'
    CBLUEBG   = '\33[44m'
    CVIOLETBG = '\33[45m'
    CBEIGEBG  = '\33[46m'
    CWHITEBG  = '\33[47m'

    CGREY    = '\33[90m'
    CRED2    = '\33[91m'
    CGREEN2  = '\33[92m'
    CYELLOW2 = '\33[93m'
    CBLUE2   = '\33[94m'
    CVIOLET2 = '\33[95m'
    CBEIGE2  = '\33[96m'
    CWHITE2  = '\33[97m'

    CGREYBG    = '\33[100m'
    CREDBG2    = '\33[101m'
    CGREENBG2  = '\33[102m'
    CYELLOWBG2 = '\33[103m'
    CBLUEBG2   = '\33[104m'
    CVIOLETBG2 = '\33[105m'
    CBEIGEBG2  = '\33[106m'
    CWHITEBG2  = '\33[107m'
        
def windowsway():
    print(bcolors.CGREEN +'[INFO]下载jre中'+bcolors.CEND)#todo processbar
    import tarfile
    if checkbit() == 64:
        file = download(windowsurl64)
    else:
        file = download(windowsurl)
    print(bcolors.CGREEN + '[INFO]正在解压中'+bcolors.CEND)
    if os.path.exists(home + '/.JSMOD2') == 0:
        os.mkdir(home + '/.JSMOD2')
        os.mkdir(home + '/.JSMOD2/java')
    os.rename(file, home + '/.JSMOD2/java/jre.tar.gz')
    tar = tarfile.open(home+'/.JSMOD2/java/jre.tar.gz')
    tar.extractall(path=home + "/.JSMOD2/java/")
    print(bcolors.CGREEN + '[INFO]完成安装java'+bcolors.CEND)
def linuxway():
    print(bcolors.CGREEN +'[INFO]下载jre中'+bcolors.CEND)#todo processbar
    import tarfile
    if checkbit() == 64:
        file = download(linuxurl64)
    else:
        file = download(linuxurl)
    print(bcolors.CGREEN + '[INFO]正在解压中'+bcolors.CEND)
    if os.path.exists(home + '/.JSMOD2') == 0:
        os.mkdir(home + '/.JSMOD2')
        os.mkdir(home + '/.JSMOD2/java')
    os.rename(file, home + '/.JSMOD2/java/jre.tar.gz')
    tar = tarfile.open(home + '/.JSMOD2/java/jre.tar.gz')
    tar.extractall(path=home + "/.JSMOD2/java/")
    print(bcolors.CGREEN + '[INFO]完成安装java'+bcolors.CEND)
def windowsrun():
    print(bcolors.CGREEN + '[INFO]正在启动中'+bcolors.CEND)
    subprocess.Popen([home+'\.JSMOD2\java\jre1.8.0_211\bin\java.exe','-jar',os.path.abspath(os.path.join(os.getcwd(), ".."))+'\jsmod2.jar'])
    sys.exit(0)
def linuxrun():
    print(bcolors.CGREEN + '[INFO]正在启动中'+bcolors.CEND)
    subprocess.Popen([home+'/.JSMOD2/java/jre1.8.0_211/bin/java','-jar',os.path.abspath(os.path.join(os.getcwd(), ".."))+ '/jsmod2.jar'])
    sys.exit(0)
def checkbit():
    import struct
    return (struct.calcsize('P') * 8)
def download( url):
    local_filename = url.split('/')[-1]
    # NOTE the stream=True parameter
    r = get(url, stream=True)
    b = 0
    with open(local_filename, 'wb+') as f:
        for chunk in r.iter_content(chunk_size=5120):
            if chunk:  # filter out keep-alive new chunks
                f.write(chunk)
                f.flush()
                b = b + 1
    return local_filename
if platform.system() == 'Linux':
    osystem = 'linux'
    #print('[DEBUG]Linux')
if platform.system() == 'Windows':
    osystem = 'windows'
    #print('[DEBUG]windows')
if platform.system() == 'Darwin':
    #Macos
    print(bcolors.CRED + '[WARN]你的系统不支持'+bcolors.CEND)
    #print('[DEBUG]MACOS')
    os._exit(0)
print(bcolors.CGREEN + '     JSMOD2启动脚本'+bcolors.CEND)
print(bcolors.CYELLOW + '   |启动jsmod2     |[0]'+bcolors.CEND)
print(bcolors.CYELLOW + '   |安装JAVA       |[1]'+bcolors.CEND)
print(bcolors.CYELLOW + '   |安装java并启动 |[2]'+bcolors.CEND)
print(bcolors.CYELLOW + '   |退出           |[3]'+bcolors.CEND)
a = input("请输入数字:")
try:
    a = int(a)
except ValueError:
    sys.exit(0)
if a == 0:
    #print('[DEBUG]0')
    if osystem == 'windows':
        windowsrun()
    else:
        linuxrun()
if a == 1:
    if osystem == 'windows':
        windowsway()
    else:
        linuxway()
if a == 2:
    if osystem == 'windows':
        windowsway()
        windowsrun()
    else:
        linuxway()
        windowsrun()
if a == 3:
    os._exit(0)
else:
    os._exit(0)
print(home)
