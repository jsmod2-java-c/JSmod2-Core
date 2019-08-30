# -*- coding: utf-8 -*-
"""
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
"""
import glob
import os
import platform
import re
import subprocess
import matplotlib.pyplot as plt

from requests import get
from os.path import expanduser
from xmlrpc.client import *

home = expanduser('~')

linuxurl = 'https://dl.jymc.top/java/jre_x86.tar.gz'
linuxurl64 = 'https://dl.jymc.top/java/jre_x64.tar.gz'
# 采用我的下载服务器下载java
windowsurl = 'https://dl.jymc.top/java/windows-i586.tar.gz'
windowsurl64 = 'https://dl.jymc.top/java/windows-x64.tar.gz'


# 采用我的下载服务器下载java

def installwindows():
	import tarfile
	if checkbit() == 64:
		file = download(windowsurl64)
	else:
		file = download(windowsurl)
	if os.path.exists(home + '/.JSMOD2') == 0:
		os.mkdir(home + '/.JSMOD2')
		os.mkdir(home + '/.JSMOD2/java')
	os.rename(file, home + '/.JSMOD2/java/jre.tar.gz')
	tar = tarfile.open(home + '/.JSMOD2/java/jre.tar.gz')
	tar.extractall(path=home + "/.JSMOD2/java/")


def installlinux():
	import tarfile
	if checkbit() == 64:
		file = download(linuxurl64)
	else:
		file = download(linuxurl)
	if os.path.exists(home + '/.JSMOD2') == 0:
		os.mkdir(home + '/.JSMOD2')
		os.mkdir(home + '/.JSMOD2/java')
	os.rename(file, home + '/.JSMOD2/java/jre.tar.gz')
	tar = tarfile.open(home + '/.JSMOD2/java/jre.tar.gz')
	tar.extractall(path=home + "/.JSMOD2/java/")


# def windowsrun():
#     subprocess.Popen([home+r'\.JSMOD2\java\jre1.8.0_221\bin\java.exe','-jar',
#     os.path.abspath(os.path.join(os.getcwd(), ".."))+'\jsmod2.jar'])

def runprocess(command):
	global p
	p = subprocess.Popen(command,
	                     stdout=subprocess.PIPE,
	                     stderr=subprocess.STDOUT)
	return iter(p.stdout.readline, b'')


def checkbit():
	import struct
	return struct.calcsize('P') * 8


def download(url):
	local_filename = url.split('/')[-1]
	# NOTE the stream=True parameter
	r = get(url, stream=True)
	with open(local_filename, 'wb+') as f:
		for chunk in r.iter_content(chunk_size=5120):
			if chunk:  # filter out keep-alive new chunks
				f.write(chunk)
				f.flush()
				return local_filename


if platform.system() == 'Linux':
	system = 'linux'
if platform.system() == 'Windows':
	system = 'windows'
# if platform.system() == 'Darwin':
# 	os._exit(0)
# Form implementation generated from reading ui file 'Jsmod2Manager.ui'
#
# Created by: PyQt5 UI code generator 5.13.0
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtWidgets
from PyQt5.QtCore import pyqtSignal
from PyQt5.QtWidgets import QApplication, QMainWindow

# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'JSmod2Manager.ui'
#
# Created by: PyQt5 UI code generator 5.13.0
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets


class Ui_JSmod2Manager(object):
	def setupUi(self, JSmod2Manager):
		JSmod2Manager.setObjectName("JSmod2Manager")
		JSmod2Manager.resize(932, 450)
		self.treeWidget = QtWidgets.QTreeWidget(JSmod2Manager)
		self.treeWidget.setGeometry(QtCore.QRect(0, -20, 131, 621))
		self.treeWidget.setEditTriggers(
			QtWidgets.QAbstractItemView.DoubleClicked | QtWidgets.QAbstractItemView.EditKeyPressed)
		self.treeWidget.setRootIsDecorated(True)
		self.treeWidget.setObjectName("treeWidget")
		item_0 = QtWidgets.QTreeWidgetItem(self.treeWidget)
		item_0 = QtWidgets.QTreeWidgetItem(self.treeWidget)
		item_0 = QtWidgets.QTreeWidgetItem(self.treeWidget)
		item_0 = QtWidgets.QTreeWidgetItem(self.treeWidget)
		item_0 = QtWidgets.QTreeWidgetItem(self.treeWidget)
		item_0 = QtWidgets.QTreeWidgetItem(self.treeWidget)
		item_0 = QtWidgets.QTreeWidgetItem(self.treeWidget)
		self.tabWidget = QtWidgets.QTabWidget(JSmod2Manager)
		self.tabWidget.setGeometry(QtCore.QRect(130, -30, 801, 561))
		self.tabWidget.setDocumentMode(True)
		self.tabWidget.setObjectName("tabWidget")
		self.Overall = QtWidgets.QWidget()
		self.Overall.setObjectName("Overall")
		self.cpu = QtWidgets.QLabel(self.Overall)
		self.cpu.setGeometry(QtCore.QRect(0, 90, 91, 16))
		self.cpu.setObjectName("cpu")
		self.ram = QtWidgets.QLabel(self.Overall)
		self.ram.setGeometry(QtCore.QRect(220, 90, 91, 17))
		self.ram.setObjectName("ram")
		self.uptime = QtWidgets.QLabel(self.Overall)
		self.uptime.setGeometry(QtCore.QRect(0, 0, 111, 17))
		self.uptime.setObjectName("uptime")
		self.tabWidget.addTab(self.Overall, "")
		self.Console = QtWidgets.QWidget()
		self.Console.setObjectName("Console")
		self.Command = QtWidgets.QLineEdit(self.Console)
		self.Command.setGeometry(QtCore.QRect(0, 370, 671, 41))
		self.Command.setObjectName("Command")
		self.Output = QtWidgets.QTextBrowser(self.Console)
		self.Output.setGeometry(QtCore.QRect(0, 0, 671, 371))
		self.Output.setObjectName("Output")
		self.Execute = QtWidgets.QPushButton(self.Console)
		self.Execute.setGeometry(QtCore.QRect(670, 370, 111, 41))
		self.Execute.setObjectName("Execute")
		self.Start = QtWidgets.QPushButton(self.Console)
		self.Start.setGeometry(QtCore.QRect(674, 40, 121, 33))
		self.Start.setObjectName("Start")
		self.Stop = QtWidgets.QPushButton(self.Console)
		self.Stop.setGeometry(QtCore.QRect(674, 90, 121, 33))
		self.Stop.setObjectName("Stop")
		self.Status = QtWidgets.QLabel(self.Console)
		self.Status.setGeometry(QtCore.QRect(670, 10, 131, 21))
		self.Status.setObjectName("Status")
		self.tabWidget.addTab(self.Console, "")
		self.Plugins = QtWidgets.QWidget()
		self.Plugins.setObjectName("Plugins")
		self.Load = QtWidgets.QPushButton(self.Plugins)
		self.Load.setGeometry(QtCore.QRect(740, 0, 61, 33))
		self.Load.setObjectName("Load")
		self.Unload = QtWidgets.QPushButton(self.Plugins)
		self.Unload.setGeometry(QtCore.QRect(740, 30, 61, 33))
		self.Unload.setObjectName("Unload")
		self.PluginsList = QtWidgets.QListWidget(self.Plugins)
		self.PluginsList.setGeometry(QtCore.QRect(0, 0, 741, 191))
		self.PluginsList.setObjectName("PluginsList")
		self.Disable = QtWidgets.QPushButton(self.Plugins)
		self.Disable.setGeometry(QtCore.QRect(740, 60, 61, 33))
		self.Disable.setObjectName("Disable")
		self.Enable = QtWidgets.QPushButton(self.Plugins)
		self.Enable.setGeometry(QtCore.QRect(740, 90, 61, 33))
		self.Enable.setObjectName("Enable")
		self.Plugininfo = QtWidgets.QTextBrowser(self.Plugins)
		self.Plugininfo.setGeometry(QtCore.QRect(0, 190, 741, 221))
		self.Plugininfo.setObjectName("Plugininfo")
		self.tabWidget.addTab(self.Plugins, "")
		self.Player = QtWidgets.QWidget()
		self.Player.setObjectName("Player")
		self.PlayerList = QtWidgets.QTableWidget(self.Player)
		self.PlayerList.setEnabled(False)
		self.PlayerList.setGeometry(QtCore.QRect(0, 10, 601, 451))
		self.PlayerList.setObjectName("PlayerList")
		self.PlayerList.setColumnCount(6)
		self.PlayerList.setRowCount(0)
		item = QtWidgets.QTableWidgetItem()
		self.PlayerList.setHorizontalHeaderItem(0, item)
		item = QtWidgets.QTableWidgetItem()
		self.PlayerList.setHorizontalHeaderItem(1, item)
		item = QtWidgets.QTableWidgetItem()
		self.PlayerList.setHorizontalHeaderItem(2, item)
		item = QtWidgets.QTableWidgetItem()
		self.PlayerList.setHorizontalHeaderItem(3, item)
		item = QtWidgets.QTableWidgetItem()
		self.PlayerList.setHorizontalHeaderItem(4, item)
		item = QtWidgets.QTableWidgetItem()
		self.PlayerList.setHorizontalHeaderItem(5, item)
		self.KickButton = QtWidgets.QPushButton(self.Player)
		self.KickButton.setEnabled(False)
		self.KickButton.setGeometry(QtCore.QRect(724, 120, 61, 33))
		self.KickButton.setObjectName("KickButton")
		self.BanButton = QtWidgets.QPushButton(self.Player)
		self.BanButton.setEnabled(False)
		self.BanButton.setGeometry(QtCore.QRect(724, 90, 61, 33))
		self.BanButton.setObjectName("BanButton")
		self.PlayerCount = QtWidgets.QLabel(self.Player)
		self.PlayerCount.setGeometry(QtCore.QRect(600, 70, 101, 17))
		self.PlayerCount.setObjectName("PlayerCount")
		self.BanTimeSpin = QtWidgets.QSpinBox(self.Player)
		self.BanTimeSpin.setEnabled(False)
		self.BanTimeSpin.setGeometry(QtCore.QRect(660, 90, 51, 31))
		self.BanTimeSpin.setObjectName("BanTimeSpin")
		self.BanTimeLabel = QtWidgets.QLabel(self.Player)
		self.BanTimeLabel.setGeometry(QtCore.QRect(600, 100, 55, 17))
		self.BanTimeLabel.setObjectName("BanTimeLabel")
		self.KickLabel = QtWidgets.QLabel(self.Player)
		self.KickLabel.setGeometry(QtCore.QRect(600, 130, 131, 17))
		self.KickLabel.setStyleSheet("font: 10pt \"文泉驿微米黑\";\n"
		                             "text-decoration: line-through;")
		self.KickLabel.setObjectName("KickLabel")
		self.tabWidget.addTab(self.Player, "")
		self.Settings = QtWidgets.QWidget()
		self.Settings.setObjectName("Settings")
		self.weboption = QtWidgets.QCheckBox(self.Settings)
		self.weboption.setGeometry(QtCore.QRect(10, 20, 85, 21))
		self.weboption.setObjectName("weboption")
		self.uoption = QtWidgets.QCheckBox(self.Settings)
		self.uoption.setGeometry(QtCore.QRect(10, 50, 85, 21))
		self.uoption.setObjectName("uoption")
		self.lroption = QtWidgets.QCheckBox(self.Settings)
		self.lroption.setGeometry(QtCore.QRect(10, 80, 151, 21))
		self.lroption.setObjectName("lroption")
		self.lmoption = QtWidgets.QCheckBox(self.Settings)
		self.lmoption.setGeometry(QtCore.QRect(10, 110, 221, 21))
		self.lmoption.setObjectName("lmoption")
		self.githuboption = QtWidgets.QCheckBox(self.Settings)
		self.githuboption.setGeometry(QtCore.QRect(10, 140, 141, 21))
		self.githuboption.setObjectName("githuboption")
		self.noption = QtWidgets.QCheckBox(self.Settings)
		self.noption.setGeometry(QtCore.QRect(10, 170, 121, 21))
		self.noption.setObjectName("optionn")
		self.jvm = QtWidgets.QTextEdit(self.Settings)
		self.jvm.setGeometry(QtCore.QRect(10, 190, 201, 31))
		self.jvm.setObjectName("jvm")
		self.rpcserver = QtWidgets.QTextEdit(self.Settings)
		self.rpcserver.setGeometry(QtCore.QRect(10, 230, 201, 31))
		self.rpcserver.setObjectName("rpcserver")
		self.rpcport = QtWidgets.QSpinBox(self.Settings)
		self.rpcport.setGeometry(QtCore.QRect(220, 230, 71, 31))
		self.rpcport.setMaximum(65535)
		self.rpcport.setProperty("value", 20020)
		self.rpcport.setObjectName("rpcport")
		self.save = QtWidgets.QPushButton(self.Settings)
		self.save.setGeometry(QtCore.QRect(710, 380, 85, 33))
		self.save.setObjectName("save")
		self.tabWidget.addTab(self.Settings, "")
		self.tab_8 = QtWidgets.QWidget()
		self.tab_8.setObjectName("tab_8")
		self.javainstall = QtWidgets.QPushButton(self.tab_8)
		self.javainstall.setGeometry(QtCore.QRect(0, 20, 85, 33))
		self.javainstall.setObjectName("javainstall")
		self.javalabel = QtWidgets.QLabel(self.tab_8)
		self.javalabel.setGeometry(QtCore.QRect(0, 0, 55, 17))
		self.javalabel.setObjectName("javalabel")
		self.jsmod2label = QtWidgets.QLabel(self.tab_8)
		self.jsmod2label.setGeometry(QtCore.QRect(0, 60, 55, 17))
		self.jsmod2label.setObjectName("jsmod2label")
		self.smod2label = QtWidgets.QLabel(self.tab_8)
		self.smod2label.setGeometry(QtCore.QRect(0, 120, 55, 17))
		self.smod2label.setObjectName("smod2label")
		self.jsmod2install = QtWidgets.QPushButton(self.tab_8)
		self.jsmod2install.setGeometry(QtCore.QRect(0, 80, 85, 33))
		self.jsmod2install.setObjectName("jsmod2install")
		self.smod2install = QtWidgets.QPushButton(self.tab_8)
		self.smod2install.setGeometry(QtCore.QRect(0, 140, 85, 33))
		self.smod2install.setObjectName("smod2install")
		self.javaopenfolder = QtWidgets.QPushButton(self.tab_8)
		self.javaopenfolder.setGeometry(QtCore.QRect(90, 20, 85, 33))
		self.javaopenfolder.setObjectName("javaopenfolder")
		self.jsmod2openfolder = QtWidgets.QPushButton(self.tab_8)
		self.jsmod2openfolder.setGeometry(QtCore.QRect(90, 80, 85, 33))
		self.jsmod2openfolder.setObjectName("jsmod2openfolder")
		self.smod2openfolder = QtWidgets.QPushButton(self.tab_8)
		self.smod2openfolder.setGeometry(QtCore.QRect(90, 140, 85, 33))
		self.smod2openfolder.setObjectName("smod2openfolder")
		self.tabWidget.addTab(self.tab_8, "")
		self.About = QtWidgets.QWidget()
		self.About.setObjectName("About")
		self.about = QtWidgets.QTextEdit(self.About)
		self.about.setGeometry(QtCore.QRect(0, -10, 801, 411))
		sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Ignored, QtWidgets.QSizePolicy.Ignored)
		sizePolicy.setHorizontalStretch(0)
		sizePolicy.setVerticalStretch(0)
		sizePolicy.setHeightForWidth(self.about.sizePolicy().hasHeightForWidth())
		self.about.setSizePolicy(sizePolicy)
		self.about.setVerticalScrollBarPolicy(QtCore.Qt.ScrollBarAsNeeded)
		self.about.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAsNeeded)
		self.about.setObjectName("about")
		self.tabWidget.addTab(self.About, "")

		self.retranslateUi(JSmod2Manager)
		self.tabWidget.setCurrentIndex(6)
		QtCore.QMetaObject.connectSlotsByName(JSmod2Manager)

	def retranslateUi(self, JSmod2Manager):
		_translate = QtCore.QCoreApplication.translate
		JSmod2Manager.setWindowTitle(_translate("JSmod2Manager", "JSmod2Manager"))
		self.treeWidget.headerItem().setText(0, _translate("JSmod2Manager", "1"))
		__sortingEnabled = self.treeWidget.isSortingEnabled()
		self.treeWidget.setSortingEnabled(False)
		self.treeWidget.topLevelItem(0).setText(0, _translate("JSmod2Manager", "Overall"))
		self.treeWidget.topLevelItem(1).setText(0, _translate("JSmod2Manager", "Console"))
		self.treeWidget.topLevelItem(2).setText(0, _translate("JSmod2Manager", "Plugins"))
		self.treeWidget.topLevelItem(3).setText(0, _translate("JSmod2Manager", "Players"))
		self.treeWidget.topLevelItem(4).setText(0, _translate("JSmod2Manager", "Settings"))
		self.treeWidget.topLevelItem(5).setText(0, _translate("JSmod2Manager", "Environment"))
		self.treeWidget.topLevelItem(6).setText(0, _translate("JSmod2Manager", "About"))
		self.treeWidget.setSortingEnabled(__sortingEnabled)
		self.cpu.setText(_translate("JSmod2Manager", "cpu:"))
		self.ram.setText(_translate("JSmod2Manager", "ram:"))
		self.uptime.setText(_translate("JSmod2Manager", "uptime:"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.Overall), _translate("JSmod2Manager", "Overall"))
		self.Execute.setText(_translate("JSmod2Manager", "Execute"))
		self.Execute.setShortcut(_translate("JSmod2Manager", "Return"))
		self.Start.setText(_translate("JSmod2Manager", "Start"))
		self.Stop.setText(_translate("JSmod2Manager", "Stop"))
		self.Status.setText(_translate("JSmod2Manager", "Status:"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.Console), _translate("JSmod2Manager", "Console"))
		self.Load.setText(_translate("JSmod2Manager", "Load"))
		self.Unload.setText(_translate("JSmod2Manager", "Unload"))
		self.Disable.setText(_translate("JSmod2Manager", "Disable"))
		self.Enable.setText(_translate("JSmod2Manager", "Enable"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.Plugins), _translate("JSmod2Manager", "Plugins"))
		item = self.PlayerList.horizontalHeaderItem(0)
		item.setText(_translate("JSmod2Manager", "Name"))
		item = self.PlayerList.horizontalHeaderItem(1)
		item.setText(_translate("JSmod2Manager", "IP"))
		item = self.PlayerList.horizontalHeaderItem(2)
		item.setText(_translate("JSmod2Manager", "Country"))
		item = self.PlayerList.horizontalHeaderItem(3)
		item.setText(_translate("JSmod2Manager", "Steamid"))
		item = self.PlayerList.horizontalHeaderItem(4)
		item.setText(_translate("JSmod2Manager", "Uid"))
		item = self.PlayerList.horizontalHeaderItem(5)
		item.setText(_translate("JSmod2Manager", "Class"))
		self.KickButton.setText(_translate("JSmod2Manager", "Kick"))
		self.BanButton.setText(_translate("JSmod2Manager", "Ban"))
		self.PlayerCount.setText(_translate("JSmod2Manager", "Players:0/0"))
		self.BanTimeLabel.setText(_translate("JSmod2Manager", "Minutes:"))
		self.KickLabel.setText(_translate("JSmod2Manager", "SCP : Kick Simulator"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.Player), _translate("JSmod2Manager", "Player"))
		self.weboption.setText(_translate("JSmod2Manager", "打开web"))
		self.uoption.setText(_translate("JSmod2Manager", "打开ui"))
		self.lroption.setText(_translate("JSmod2Manager", "打开round的log监听"))
		self.lmoption.setText(_translate("JSmod2Manager", "打开multiAdmin和游戏的log监听"))
		self.githuboption.setText(_translate("JSmod2Manager", "打开和Github连接"))
		self.noption.setText(_translate("JSmod2Manager", "打开client处理"))
		self.jvm.setPlaceholderText(_translate("JSmod2Manager", "JVM参数"))
		self.rpcserver.setPlaceholderText(_translate("JSmod2Manager", "RPC服务器IP"))
		self.save.setText(_translate("JSmod2Manager", "保存"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.Settings), _translate("JSmod2Manager", "Settings"))
		self.javainstall.setText(_translate("JSmod2Manager", "Install"))
		self.javalabel.setText(_translate("JSmod2Manager", "Java"))
		self.jsmod2label.setText(_translate("JSmod2Manager", "JSmod2"))
		self.smod2label.setText(_translate("JSmod2Manager", "Smod2"))
		self.jsmod2install.setText(_translate("JSmod2Manager", "Install"))
		self.smod2install.setText(_translate("JSmod2Manager", "Install"))
		self.javaopenfolder.setText(_translate("JSmod2Manager", "Open Folder"))
		self.jsmod2openfolder.setText(_translate("JSmod2Manager", "Open Folder"))
		self.smod2openfolder.setText(_translate("JSmod2Manager", "Open Folder"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_8), _translate("JSmod2Manager", "Environment"))
		self.about.setHtml(_translate("JSmod2Manager",
		                              "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
		                              "<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
		                              "p, li { white-space: pre-wrap; }\n"
		                              "</style></head><body style=\" font-family:\'文泉驿微米黑\'; font-size:10pt; font-weight:400; font-style:normal;\">\n"
		                              "<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">            Thanks to                                 </p>\n"
		                              "<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Designer:fafa_junhe</p>\n"
		                              "<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Coder:fafa_junhe</p>\n"
		                              "<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Thanks to JSmod2</p>\n"
		                              "<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Thanks to Smod2</p>\n"
		                              "<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">Thanks to CCTV <a name=\"taag_output_text\"></a><span style=\" font-family:\'monospace\'; color:#000000; background-color:#ffffff;\"> </span><span style=\" font-family:\'monospace\'; color:#000000; background-color:#ffffff;\">                                                                               </span></p>\n"
		                              "<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:15px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px; background-color:#ffffff;\"><br /></p></body></html>"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.About), _translate("JSmod2Manager", "About"))
		self.treeWidget.itemSelectionChanged.connect(self.detect)
		self.Disable.pressed.connect(self.disable)
		self.Load.pressed.connect(self.loadplugin)
		self.Enable.pressed.connect(self.enable)
		self.Unload.pressed.connect(self.unloadplugin)
		self.Start.pressed.connect(self.start)
		self.Stop.pressed.connect(self.stop)
		self.Execute.pressed.connect(self.execute)
		self.tabWidget.setCurrentIndex(0)
		QtCore.QMetaObject.connectSlotsByName(JSmod2Manager)
		self.jsmod2_thread = self.jsmod2Thread()
		self.jsmod2_thread.signal.connect(self.appendtext)
		self.jsmod2_thread.start()
		self.PluginsList.itemClicked.connect(self.checktype)
		self.refreshpluginlist()
		self.PluginsList.itemSelectionChanged.connect(self.getplugininfo)
		self.Start.setDisabled(0)
		self.Stop.setDisabled(0)
		time.sleep(1)
		self.Load.setDisabled(1)
		self.Unload.setDisabled(1)
		self.Disable.setDisabled(1)
		self.Enable.setDisabled(1)
		self.PluginsList.setDisabled(1)
		jsmod2option = keyvalue["jsmod2.option"]
		print(jsmod2option)
		if jsmod2option.find("1") > -1:
			self.weboption.setChecked(1)
		if jsmod2option.find("2") > -1:
			self.uoption.setChecked(1)
		if jsmod2option.find("3") > -1:
			self.lroption.setChecked(1)
		if jsmod2option.find("4") > -1:
			self.lmoption.setChecked(1)
		if jsmod2option.find("5") > -1:
			self.githuboption.setChecked(1)
		if jsmod2option.find("6") > -1:
			self.noption.setChecked(1)
		self.rpcserver.setText(keyvalue["rpc.server"])
		self.rpcport.setValue(int(keyvalue["rpc.port"]))
		self.jvm.setText(keyvalue["jvm.option"])
		self.weboption.stateChanged.connect(self.changejsmod2option)
		self.uoption.stateChanged.connect(self.changejsmod2option)
		self.lroption.stateChanged.connect(self.changejsmod2option)
		self.lmoption.stateChanged.connect(self.changejsmod2option)
		self.githuboption.stateChanged.connect(self.changejsmod2option)
		self.noption.stateChanged.connect(self.changejsmod2option)
		self.jvm.textChanged.connect(self.changejsmod2option)
		self.rpcserver.textChanged.connect(self.changejsmod2option)
		self.rpcport.valueChanged.connect(self.changejsmod2option)
		global HeartBeatThread
		HeartBeatThread = heartbeatThread()
		HeartBeatThread.start()
		HeartBeatThread.signal.connect(self.changestatus)
	def changejsmod2option(self):
		a = ""
		if self.weboption.isChecked():
			a = a + "1"
		if self.uoption.isChecked():
			a = a + "2"
		if self.lroption.isChecked():
			a = a + "3"
		if self.lmoption.isChecked():
			a = a + "4"
		if self.githuboption.isChecked():
			a = a + "5"
		if self.noption.isChecked():
			a = a + "6"
		keyvalue["jsmod2.option"] = a
		keyvalue["rpc.port"] = self.rpcport.value()
		keyvalue["rpc.server"] = self.rpcserver.toPlainText()
		keyvalue["jvm.option"] = self.jvm.toPlainText()
		print(keyvalue)
		emm =str(keyvalue).replace("{","").replace("}","").replace("\'","").replace(":","=").replace(",","\n").replace(" ","")
		properties = open(os.getcwd() + "/../manager.properties", "w")
		print(properties.write(emm))
		properties.close()
	def loadplugin(self):
		rpcclient.plugin_load(self.PluginsList.currentItem().text())

	def unloadplugin(self):
		rpcclient.plugin_unload(self.PluginsList.currentItem().text())

	def getplugininfo(self):
		print()
		self.Plugininfo.setText(rpcclient.plugin_info(self.PluginsList.currentItem().text()))

	def checktype(self, item):
		if item.text().find(".jar") >= 1:
			self.Disable.setDisabled(0)
			self.Enable.setDisabled(1)
			self.Load.setDisabled(0)
			self.Unload.setDisabled(0)
		if item.text().find(".disabled") >= 1:
			self.Enable.setDisabled(0)
			self.Disable.setDisabled(1)
			self.Load.setDisabled(1)
			self.Unload.setDisabled(1)

	def refreshpluginlist(self):
		self.PluginsList.clear()
		enabled = []
		for file in glob.glob(os.getcwd() + "/../plugins/*.jar"):
			enabled.append(file)
			self.PluginsList.addItem(file.split("/")[-1:][0])
		disabled = []
		for file in glob.glob(os.getcwd() + "/../plugins/*.disabled"):
			disabled.append(file)
			self.PluginsList.addItem(file.split("/")[-1:][0])

	def disable(self):
		plugin = self.PluginsList.currentItem().text()
		self.unloadplugin()
		os.rename(os.getcwd() + "/../plugins/" + plugin,
		          (os.getcwd() + "/../plugins/" + plugin + ".disabled").replace(".jar", ""))
		self.refreshpluginlist()

	def enable(self):
		plugin = self.PluginsList.currentItem().text()
		os.rename(os.getcwd() + "/../plugins/" + plugin,
		          (os.getcwd() + "/../plugins/" + plugin + ".jar").replace(".disabled", ""))
		self.loadplugin()
		self.refreshpluginlist()

	class jsmod2Thread(QtCore.QThread):
		signal = pyqtSignal('PyQt_PyObject')

		def __init__(self):
			QtCore.QThread.__init__(self)

		def __del__(self):
			self.wait()

		def run(self):
			try:
				for line in runprocess((home + r'/.JSMOD2/java/jre1.8.0_211/bin/java -jar ' + os.path.abspath(
						os.path.join(os.getcwd(), "..")) + '/jsmod2.jar -rpc '+ keyvalue["rpc.port"]).split()):
					self.signal.emit(line.decode().replace("\n", ""))
			except:
				try:
					for line in runprocess((r'java -jar ' + os.path.abspath(
							os.path.join(os.getcwd(), "..")) + '/jsmod2.jar -rpc '+ keyvalue["rpc.port"]).split()):
						self.signal.emit(line.decode().replace("\n", ""))
				except:
					print("您的系统不支持并且系统没有java too sad :(")
					sys.exit(1)

	def detect(self):
		print(self.treeWidget.selectedIndexes())
		getSelected = self.treeWidget.selectedItems()
		if getSelected:
			baseNode = getSelected[0]
			self.tabWidget.setCurrentIndex(self.treeWidget.indexFromItem(baseNode).row())

	def start(self):
		if self.jsmod2_thread.isRunning():
			ServerThread = serverthread()
			ServerThread.start()

		else:
			self.jsmod2_thread = self.jsmod2Thread()
			self.jsmod2_thread.signal.connect(self.appendtext)
			self.jsmod2_thread.start()
	# ServerThread = serverthread()
	# ServerThread.start()
	# HeartBeatThread = heartbeatThread()
	# HeartBeatThread.start()
	# HeartBeatThread.signal.connect(self.changestatus)

	def appendtext(self, output):
		if output.find("xmlrpc") > 0:
			self.startrpcclient()
			return
		highlighter = [("[0m",QtGui.QColor("black")),("[30m",QtGui.QColor("black")),("[31m",QtGui.QColor("red")),("[32m",QtGui.QColor("green")),("[33m",QtGui.QColor("green")),("[34m",QtGui.QColor("blue")),("[35m",QtGui.QColor("magenta")),("[36m",QtGui.QColor("cyan")),("[37m",QtGui.QColor("white"))]
		list = output.split("\033")
		for word in list:
			for word2,color in highlighter:
				if word.find(word2) >= 0:
					self.Output.setTextColor(color)
					self.Output.insertPlainText(word.replace(word2,""))
		self.Output.insertPlainText("\n")
		self.Output.moveCursor(QtGui.QTextCursor.End)


	def changestatus(self, output):
		print(output)
		self.cpu.setText("cpu:" + str(output[1]))
		self.ram.setText("ram:" + str(output[2]))
		self.uptime.setText("uptime:" + str(output[3]))
		self.Start.setDisabled(0)
		self.Stop.setDisabled(1)
		self.PluginsList.setDisabled(1)
		if output[0] == "stop":
			self.Start.setDisabled(0)
			self.Stop.setDisabled(1)
			self.PluginsList.setDisabled(1)
		if output[0] == "connected":
			self.Start.setDisabled(1)
			self.PluginsList.setDisabled(0)
			self.Stop.setDisabled(0)
		if output[0] == "disconnected":
			self.Start.setDisabled(1)
			self.Stop.setDisabled(0)
			self.PluginsList.setDisabled(1)

		self.Status.setText("Status:" + output[0])

	def stop(self):
		ServerStopThread = serverstopThread()
		ServerStopThread.start()

	def startrpcclient(self):
		global rpcclient
		rpcclient = rpcclientThread()
		rpcclient.start()

	# rpcclient.execute("say test")

	def execute(self):
		try:
			rpcclient.execute(self.Command.text())
		except Error as a:
			print(a)
			return


class serverthread(QtCore.QThread):
	def __init__(self):
		QtCore.QThread.__init__(self)

	def run(self):
		rpcclient.startserver()
		print("finish")
	def __del__(self):
		self.wait()


class heartbeatThread(QtCore.QThread):
	signal = pyqtSignal('PyQt_PyObject')

	def __init__(self):
		QtCore.QThread.__init__(self)

	def run(self):
		# self.msleep(3)
		while 1:
			list = []
			try:
				self.sleep(1)
				status = rpcclient.getstatus()
				cpu = rpcclient.cpu()
				ram = rpcclient.ram()
				uptime = rpcclient.uptime()
			except:
				status = "stop"
				cpu = ""
				ram = ""
				uptime = ""
			list.append(status)
			list.append(cpu)
			list.append(ram)
			list.append(uptime)
			self.signal.emit(list)

	def __del__(self):
		self.wait()


class serverstopThread(QtCore.QThread):
	def __init__(self):
		QtCore.QThread.__init__(self)

	def run(self):
		rpcclient.stop()
		p.terminate()
		# try:
		# 	# HeartBeatThread.terminate()
		# except:
		# 	pass
	def __del__(self):
		self.wait()


class rpcclientThread(QtCore.QThread):
	signal = pyqtSignal('PyQt_PyObject')

	def __init__(self):
		QtCore.QThread.__init__(self)

	def __del__(self):
		self.wait()

	def run(self):
		# time.sleep(1)
		self.rpcserver = ServerProxy("http://127.0.0.1:"+keyvalue["rpc.port"])
		print("conn")
		# print(self.rpcserver.jsmod.get_handlers())
	def stop(self):
		try:
			self.rpcserver.jsmod.stop()
		except:
			pass
			# time.sleep(1)
			# HeartBeatThread.terminate()
	def startserver(self):
		try:
			print(self.rpcserver.jsmod.start(keyvalue["jsmod2.option"]))
		except:
			print("error")
			pass

	def execute(self, commmand):
		try:
			return self.rpcserver.jsmod.execute(commmand)
		except:
			pass

	def getstatus(self):
		print(self.rpcserver.jsmod.get_status())
		return (self.rpcserver.jsmod.get_status())

	def cpu(self):
		return self.rpcserver.jsmod.get_cpu()

	def ram(self):
		return self.rpcserver.jsmod.get_ram()

	def uptime(self):
		return int(self.rpcserver.jsmod.get_uptime()) / 1000

	def plugin_load(self, plugin):
		try:
			return self.rpcserver.jsmod.execute("load " + plugin)
		except:
			pass

	def plugin_unload(self, plugin):
		try:
			return self.rpcserver.jsmod.execute("unload " + plugin)
		except:
			pass

	def plugin_reload(self, plugin):
		self.rpcserver.jsmod.execute("unload " + plugin)
		time.sleep(1)
		return self.rpcserver.jsmod.execute("load " + plugin)

	def plugin_list(self):
		enabled = []
		for file in glob.glob(os.getcwd() + "/plugins/*.jar"):
			enabled.append(file)

		disabled = []
		for file in glob.glob(os.getcwd() + "/plugins/*.disabled"):
			disabled.append(file)

	def plugin_info(self, plugin):
		try:
			return (self.rpcserver.jsmod.plugin_info(plugin))
		except:
			pass

properties = open(os.getcwd() + "/../manager.properties","r+")
keyvalue = {"":""}
for line in properties.readlines():
	if line[0] == "#":
		continue
	keyvalue[line.split("=")[0]] =line.split("=")[1].replace("\n","")
del keyvalue[""]
print(keyvalue)
app = QApplication(sys.argv)
MainWindow = QMainWindow()
ui = Ui_JSmod2Manager()
ui.setupUi(MainWindow)
MainWindow.show()
# MainWindow.setFixedSize(600, 411)
MainWindow.setWindowTitle("JSmod2启动器")
app.exit(app.exec_())
