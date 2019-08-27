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
import subprocess

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
if platform.system() == 'Darwin':
	os._exit(0)
# Form implementation generated from reading ui file 'Jsmod2Manager.ui'
#
# Created by: PyQt5 UI code generator 5.13.0
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtWidgets
from PyQt5.QtCore import pyqtSignal
from PyQt5.QtWidgets import QApplication, QMainWindow


class Ui_JSmod2Manager(object):
	def setupUi(self, JSmod2Manager):
		JSmod2Manager.setObjectName("JSmod2Manager")
		JSmod2Manager.resize(597, 411)
		self.treeWidget = QtWidgets.QTreeWidget(JSmod2Manager)
		self.treeWidget.setGeometry(QtCore.QRect(0, -20, 131, 461))
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
		self.tabWidget_2 = QtWidgets.QTabWidget(JSmod2Manager)
		self.tabWidget_2.setGeometry(QtCore.QRect(120, -30, 481, 501))
		self.tabWidget_2.setObjectName("tabWidget_2")
		self.tab_3 = QtWidgets.QWidget()
		self.tab_3.setObjectName("tab_3")
		self.tabWidget = QtWidgets.QTabWidget(self.tab_3)
		self.tabWidget.setGeometry(QtCore.QRect(0, -30, 481, 471))
		self.tabWidget.setDocumentMode(True)
		self.tabWidget.setObjectName("tabWidget")
		self.tab_2 = QtWidgets.QWidget()
		self.tab_2.setObjectName("tab_2")
		self.label = QtWidgets.QLabel(self.tab_2)
		self.label.setGeometry(QtCore.QRect(30, 90, 91, 17))
		self.label.setObjectName("label")
		self.label_2 = QtWidgets.QLabel(self.tab_2)
		self.label_2.setGeometry(QtCore.QRect(220, 90, 91, 17))
		self.label_2.setObjectName("label_2")
		self.label_3 = QtWidgets.QLabel(self.tab_2)
		self.label_3.setGeometry(QtCore.QRect(0, 0, 111, 17))
		self.label_3.setObjectName("label_3")
		self.label_4 = QtWidgets.QLabel(self.tab_2)
		self.label_4.setGeometry(QtCore.QRect(30, 130, 151, 101))
		self.label_4.setObjectName("label_4")
		self.label_5 = QtWidgets.QLabel(self.tab_2)
		self.label_5.setGeometry(QtCore.QRect(220, 140, 181, 91))
		self.label_5.setObjectName("label_5")
		self.tabWidget.addTab(self.tab_2, "")
		self.tab = QtWidgets.QWidget()
		self.tab.setObjectName("tab")
		self.lineEdit = QtWidgets.QLineEdit(self.tab)
		self.lineEdit.setGeometry(QtCore.QRect(0, 370, 371, 31))
		self.lineEdit.setObjectName("lineEdit")
		self.textBrowser = QtWidgets.QTextBrowser(self.tab)
		self.textBrowser.setGeometry(QtCore.QRect(0, 0, 371, 371))
		self.textBrowser.setObjectName("textBrowser")
		self.pushButton = QtWidgets.QPushButton(self.tab)
		self.pushButton.setGeometry(QtCore.QRect(370, 370, 101, 33))
		self.pushButton.setObjectName("pushButton")
		self.pushButton_2 = QtWidgets.QPushButton(self.tab)
		self.pushButton_2.setGeometry(QtCore.QRect(370, 30, 85, 33))
		self.pushButton_2.setObjectName("pushButton_2")
		self.pushButton_3 = QtWidgets.QPushButton(self.tab)
		self.pushButton_3.setGeometry(QtCore.QRect(370, 70, 85, 33))
		self.pushButton_3.setObjectName("pushButton_3")
		self.checkBox = QtWidgets.QCheckBox(self.tab)
		self.checkBox.setGeometry(QtCore.QRect(370, 110, 85, 21))
		self.checkBox.setObjectName("checkBox")
		self.checkBox_2 = QtWidgets.QCheckBox(self.tab)
		self.checkBox_2.setGeometry(QtCore.QRect(370, 140, 85, 21))
		self.checkBox_2.setObjectName("checkBox_2")
		self.checkBox_3 = QtWidgets.QCheckBox(self.tab)
		self.checkBox_3.setGeometry(QtCore.QRect(370, 200, 85, 21))
		self.checkBox_3.setObjectName("checkBox_3")
		self.checkBox_4 = QtWidgets.QCheckBox(self.tab)
		self.checkBox_4.setGeometry(QtCore.QRect(370, 170, 85, 21))
		self.checkBox_4.setObjectName("checkBox_4")
		self.label_6 = QtWidgets.QLabel(self.tab)
		self.label_6.setGeometry(QtCore.QRect(370, 220, 81, 17))
		self.label_6.setObjectName("label_6")
		self.tabWidget.addTab(self.tab, "")
		self.tab_5 = QtWidgets.QWidget()
		self.tab_5.setObjectName("tab_5")
		self.pushButton_4 = QtWidgets.QPushButton(self.tab_5)
		self.pushButton_4.setGeometry(QtCore.QRect(410, 0, 61, 33))
		self.pushButton_4.setObjectName("pushButton_4")
		self.pushButton_5 = QtWidgets.QPushButton(self.tab_5)
		self.pushButton_5.setGeometry(QtCore.QRect(410, 30, 61, 33))
		self.pushButton_5.setObjectName("pushButton_5")
		self.listWidget = QtWidgets.QListWidget(self.tab_5)
		self.listWidget.setGeometry(QtCore.QRect(0, 0, 411, 191))
		self.listWidget.setObjectName("listWidget")
		self.pushButton_6 = QtWidgets.QPushButton(self.tab_5)
		self.pushButton_6.setGeometry(QtCore.QRect(410, 60, 61, 33))
		self.pushButton_6.setObjectName("pushButton_6")
		self.pushButton_7 = QtWidgets.QPushButton(self.tab_5)
		self.pushButton_7.setGeometry(QtCore.QRect(410, 90, 61, 33))
		self.pushButton_7.setObjectName("pushButton_7")
		self.textBrowser_3 = QtWidgets.QTextBrowser(self.tab_5)
		self.textBrowser_3.setGeometry(QtCore.QRect(0, 190, 411, 161))
		self.textBrowser_3.setObjectName("textBrowser_3")
		self.tabWidget.addTab(self.tab_5, "")
		self.tab_6 = QtWidgets.QWidget()
		self.tab_6.setObjectName("tab_6")
		self.checkBox_5 = QtWidgets.QCheckBox(self.tab_6)
		self.checkBox_5.setGeometry(QtCore.QRect(10, 20, 85, 21))
		self.checkBox_5.setObjectName("checkBox_5")
		self.checkBox_6 = QtWidgets.QCheckBox(self.tab_6)
		self.checkBox_6.setGeometry(QtCore.QRect(10, 50, 85, 21))
		self.checkBox_6.setObjectName("checkBox_6")
		self.checkBox_7 = QtWidgets.QCheckBox(self.tab_6)
		self.checkBox_7.setGeometry(QtCore.QRect(10, 80, 151, 21))
		self.checkBox_7.setObjectName("checkBox_7")
		self.checkBox_8 = QtWidgets.QCheckBox(self.tab_6)
		self.checkBox_8.setGeometry(QtCore.QRect(10, 110, 221, 21))
		self.checkBox_8.setObjectName("checkBox_8")
		self.checkBox_9 = QtWidgets.QCheckBox(self.tab_6)
		self.checkBox_9.setGeometry(QtCore.QRect(10, 140, 141, 21))
		self.checkBox_9.setObjectName("checkBox_9")
		self.checkBox_10 = QtWidgets.QCheckBox(self.tab_6)
		self.checkBox_10.setGeometry(QtCore.QRect(10, 170, 121, 21))
		self.checkBox_10.setObjectName("checkBox_10")
		self.textEdit = QtWidgets.QTextEdit(self.tab_6)
		self.textEdit.setGeometry(QtCore.QRect(10, 190, 201, 31))
		self.textEdit.setObjectName("textEdit")
		self.textEdit_2 = QtWidgets.QTextEdit(self.tab_6)
		self.textEdit_2.setGeometry(QtCore.QRect(10, 230, 201, 31))
		self.textEdit_2.setObjectName("textEdit_2")
		self.spinBox = QtWidgets.QSpinBox(self.tab_6)
		self.spinBox.setGeometry(QtCore.QRect(220, 230, 71, 31))
		self.spinBox.setMaximum(65535)
		self.spinBox.setProperty("value", 20020)
		self.spinBox.setObjectName("spinBox")
		self.pushButton_8 = QtWidgets.QPushButton(self.tab_6)
		self.pushButton_8.setGeometry(QtCore.QRect(380, 370, 85, 33))
		self.pushButton_8.setObjectName("pushButton_8")
		self.tabWidget.addTab(self.tab_6, "")
		self.tab_8 = QtWidgets.QWidget()
		self.tab_8.setObjectName("tab_8")
		self.pushButton_9 = QtWidgets.QPushButton(self.tab_8)
		self.pushButton_9.setGeometry(QtCore.QRect(0, 20, 85, 33))
		self.pushButton_9.setObjectName("pushButton_9")
		self.label_7 = QtWidgets.QLabel(self.tab_8)
		self.label_7.setGeometry(QtCore.QRect(0, 0, 55, 17))
		self.label_7.setObjectName("label_7")
		self.label_8 = QtWidgets.QLabel(self.tab_8)
		self.label_8.setGeometry(QtCore.QRect(0, 60, 55, 17))
		self.label_8.setObjectName("label_8")
		self.label_9 = QtWidgets.QLabel(self.tab_8)
		self.label_9.setGeometry(QtCore.QRect(0, 120, 55, 17))
		self.label_9.setObjectName("label_9")
		self.pushButton_10 = QtWidgets.QPushButton(self.tab_8)
		self.pushButton_10.setGeometry(QtCore.QRect(0, 80, 85, 33))
		self.pushButton_10.setObjectName("pushButton_10")
		self.pushButton_11 = QtWidgets.QPushButton(self.tab_8)
		self.pushButton_11.setGeometry(QtCore.QRect(0, 140, 85, 33))
		self.pushButton_11.setObjectName("pushButton_11")
		self.pushButton_12 = QtWidgets.QPushButton(self.tab_8)
		self.pushButton_12.setGeometry(QtCore.QRect(90, 20, 85, 33))
		self.pushButton_12.setObjectName("pushButton_12")
		self.pushButton_13 = QtWidgets.QPushButton(self.tab_8)
		self.pushButton_13.setGeometry(QtCore.QRect(90, 80, 85, 33))
		self.pushButton_13.setObjectName("pushButton_13")
		self.pushButton_14 = QtWidgets.QPushButton(self.tab_8)
		self.pushButton_14.setGeometry(QtCore.QRect(90, 140, 85, 33))
		self.pushButton_14.setObjectName("pushButton_14")
		self.tabWidget.addTab(self.tab_8, "")
		self.tab_7 = QtWidgets.QWidget()
		self.tab_7.setObjectName("tab_7")
		self.textEdit_21 = QtWidgets.QTextEdit(self.tab_7)
		self.textEdit_21.setGeometry(QtCore.QRect(0, 0, 471, 371))
		sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Ignored, QtWidgets.QSizePolicy.Ignored)
		sizePolicy.setHorizontalStretch(0)
		sizePolicy.setVerticalStretch(0)
		sizePolicy.setHeightForWidth(self.textEdit_21.sizePolicy().hasHeightForWidth())
		self.textEdit_21.setSizePolicy(sizePolicy)
		self.textEdit_21.setVerticalScrollBarPolicy(QtCore.Qt.ScrollBarAsNeeded)
		self.textEdit_21.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAsNeeded)
		self.textEdit_21.setObjectName("textEdit_21")
		self.tabWidget.addTab(self.tab_7, "")
		self.tabWidget_2.addTab(self.tab_3, "")
		self.tab_4 = QtWidgets.QWidget()
		self.tab_4.setObjectName("tab_4")
		self.tabWidget_2.addTab(self.tab_4, "")

		self.retranslateUi(JSmod2Manager)
		self.tabWidget_2.setCurrentIndex(0)
		self.tabWidget.setCurrentIndex(3)
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
		self.treeWidget.topLevelItem(3).setText(0, _translate("JSmod2Manager", "Settings"))
		self.treeWidget.topLevelItem(4).setText(0, _translate("JSmod2Manager", "Environment"))
		self.treeWidget.topLevelItem(5).setText(0, _translate("JSmod2Manager", "About"))
		self.treeWidget.setSortingEnabled(__sortingEnabled)
		self.label.setText(_translate("JSmod2Manager", "cpu usage:"))
		self.label_2.setText(_translate("JSmod2Manager", "ram usage:"))
		self.label_3.setText(_translate("JSmod2Manager", "uptime:"))
		self.label_4.setText(_translate("JSmod2Manager", "TODO cpu usage chart"))
		self.label_5.setText(_translate("JSmod2Manager", "TODO ram usage chart"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_2), _translate("JSmod2Manager", "Overall"))
		self.pushButton.setText(_translate("JSmod2Manager", "Execute"))
		self.pushButton.setShortcut(_translate("JSmod2Manager", "Return"))
		self.pushButton_2.setText(_translate("JSmod2Manager", "Start"))
		self.pushButton_3.setText(_translate("JSmod2Manager", "Stop"))
		self.checkBox.setText(_translate("JSmod2Manager", "Log"))
		self.checkBox_2.setText(_translate("JSmod2Manager", "Warn"))
		self.checkBox_3.setText(_translate("JSmod2Manager", "Crit"))
		self.checkBox_4.setText(_translate("JSmod2Manager", "Error"))
		self.label_6.setText(_translate("JSmod2Manager", "Status:"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab), _translate("JSmod2Manager", "Console"))
		self.pushButton_4.setText(_translate("JSmod2Manager", "Load"))
		self.pushButton_5.setText(_translate("JSmod2Manager", "Unload"))
		self.pushButton_6.setText(_translate("JSmod2Manager", "Disable"))
		self.pushButton_7.setText(_translate("JSmod2Manager", "Enable"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_5), _translate("JSmod2Manager", "Plugins"))
		self.checkBox_5.setText(_translate("JSmod2Manager", "打开web"))
		self.checkBox_6.setText(_translate("JSmod2Manager", "打开ui"))
		self.checkBox_7.setText(_translate("JSmod2Manager", "打开round的log监听"))
		self.checkBox_8.setText(_translate("JSmod2Manager", "打开multiAdmin和游戏的log监听"))
		self.checkBox_9.setText(_translate("JSmod2Manager", "打开和Github连接"))
		self.checkBox_10.setText(_translate("JSmod2Manager", "打开client处理"))
		self.textEdit.setPlaceholderText(_translate("JSmod2Manager", "JVM参数"))
		self.textEdit_2.setPlaceholderText(_translate("JSmod2Manager", "RPC服务器IP"))
		self.pushButton_8.setText(_translate("JSmod2Manager", "保存"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_6), _translate("JSmod2Manager", "Settings"))
		self.pushButton_9.setText(_translate("JSmod2Manager", "Install"))
		self.label_7.setText(_translate("JSmod2Manager", "Java"))
		self.label_8.setText(_translate("JSmod2Manager", "JSmod2"))
		self.label_9.setText(_translate("JSmod2Manager", "Smod2"))
		self.pushButton_10.setText(_translate("JSmod2Manager", "Install"))
		self.pushButton_11.setText(_translate("JSmod2Manager", "Install"))
		self.pushButton_12.setText(_translate("JSmod2Manager", "Open Folder"))
		self.pushButton_13.setText(_translate("JSmod2Manager", "Open Folder"))
		self.pushButton_14.setText(_translate("JSmod2Manager", "Open Folder"))
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_8), _translate("JSmod2Manager", "Environment"))
		self.textEdit_21.setHtml(_translate("JSmod2Manager",
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
		self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_7), _translate("JSmod2Manager", "About"))
		self.tabWidget_2.setTabText(self.tabWidget_2.indexOf(self.tab_3), _translate("JSmod2Manager", "Jsmod2"))
		self.tabWidget_2.setTabText(self.tabWidget_2.indexOf(self.tab_4), _translate("JSmod2Manager", "Smod2"))
		self.checkBox.setChecked(1)
		self.checkBox_2.setChecked(1)
		self.checkBox_3.setChecked(1)
		self.checkBox_4.setChecked(1)
		self.checkBox.stateChanged.connect(self.log)
		self.checkBox_2.stateChanged.connect(self.log)
		self.checkBox_3.stateChanged.connect(self.log)
		self.checkBox_4.stateChanged.connect(self.log)

		self.treeWidget.itemSelectionChanged.connect(self.detect)
		self.pushButton_6.pressed.connect(self.disable)
		self.pushButton_7.pressed.connect(self.enable)
		self.pushButton_2.pressed.connect(self.start)
		self.pushButton_3.pressed.connect(self.stop)
		self.pushButton.pressed.connect(self.execute)
		self.tabWidget_2.setCurrentIndex(0)
		self.tabWidget.setCurrentIndex(0)
		QtCore.QMetaObject.connectSlotsByName(JSmod2Manager)

		self.jsmod2_thread = self.jsmod2Thread()
		self.jsmod2_thread.signal.connect(self.appendtext)
		self.jsmod2_thread.start()
		self.listWidget.itemClicked.connect(self.checktype)
		self.refreshpluginlist()
		self.listWidget.itemSelectionChanged.connect(self.getplugininfo)
	def getplugininfo(self):
		print()
		rpcclient.plugin_info(self.listWidget.currentItem().text())
	def log(self):
		if self.checkBox.isChecked() == 1:
			pass

	def checktype(self, item):
		if item.text().find(".jar") >= 1:
			self.pushButton_6.setDisabled(0)
			self.pushButton_7.setDisabled(1)
		if item.text().find(".disabled") >= 1:
			self.pushButton_7.setDisabled(0)
			self.pushButton_6.setDisabled(1)

	def refreshpluginlist(self):
		self.listWidget.clear()
		enabled = []
		for file in glob.glob(os.getcwd() + "/../plugins/*.jar"):
			enabled.append(file)
			self.listWidget.addItem(file.split("/")[-1:][0])
		disabled = []
		for file in glob.glob(os.getcwd() + "/../plugins/*.disabled"):
			disabled.append(file)
			self.listWidget.addItem(file.split("/")[-1:][0])

	def disable(self):
		plugin = self.listWidget.currentItem().text()
		os.rename(os.getcwd() + "/../plugins/" + plugin,
		          (os.getcwd() + "/../plugins/" + plugin + ".disabled").replace(".jar", ""))
		self.refreshpluginlist()

	def enable(self):
		plugin = self.listWidget.currentItem().text()
		os.rename(os.getcwd() + "/../plugins/" + plugin,
		          (os.getcwd() + "/../plugins/" + plugin + ".jar").replace(".disabled", ""))
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
						os.path.join(os.getcwd(), "..")) + '/jsmod2.jar -rpc 20020').split()):
					self.signal.emit(line.decode().replace("\n", ""))
			except:
				try:
					for line in runprocess((r'java -jar ' + os.path.abspath(
							os.path.join(os.getcwd(), "..")) + '/jsmod2.jar -rpc 20020').split()):
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
		# rpcclient.start()
		ServerThread = serverthread()
		ServerThread.start()
		print("start")
		HeartBeatThread = heartbeatThread()
		HeartBeatThread.start()
		print("start")
		HeartBeatThread.signal.connect(self.changestatus)
		print("exit")

	def appendtext(self, output):
		if output.find("WARN") >= 1:
			output = '<span style=\" color: #f09d1f;\">%s</span>' % output
		if output.find("ERROR") >= 1:
			output = '<span style=\" color: #ff0000;\">%s</span>' % output
		if output.find("Failed") >= 1:
			output = '<span style=\" color: #ff0000;\">%s</span>' % output
		if output.find("DEBUG") >= 1:
			output = '<span style=\" color: #cccf09;\">%s</span>' % output
		if output.find("INFO") >= 1:
			output = '<span style=\" color: #208050;\">%s</span>' % output
		if output.find("xmlrpc") >= 1:
			self.startrpcclient()
			return
		self.textBrowser.append(output)

	def changestatus(self, output):
		self.label_6.setText("Status:" + output)

	def stop(self):
		ServerStopThread = serverstopThread()
		ServerStopThread.start()

	def startrpcclient(self):
		global rpcclient
		rpcclient = rpcclientThread()
		rpcclient.start()

		# rpcclient.execute("say test")

	def execute(self):
		print(self.lineEdit.text())
		rpcclient.execute(self.lineEdit.text())


class serverthread(QtCore.QThread):
	def __init__(self):
		QtCore.QThread.__init__(self)

	def run(self):
		rpcclient.startserver()

	def __del__(self):
		self.wait()


class heartbeatThread(QtCore.QThread):
	signal = pyqtSignal('PyQt_PyObject')

	def __init__(self):
		QtCore.QThread.__init__(self)

	def run(self):
		# self.msleep(3)
		while 1:
			try:
				self.sleep(1)
				status = rpcclient.getstatus()
			except:
				status = "stop"
			self.signal.emit(status)

	def __del__(self):
		self.wait()


class serverstopThread(QtCore.QThread):
	def __init__(self):
		QtCore.QThread.__init__(self)

	def run(self):
		rpcclient.stop()
		QtCore.QCoreApplication.processEvents()

	def __del__(self):
		self.wait()


class rpcclientThread(QtCore.QThread):
	signal = pyqtSignal('PyQt_PyObject')

	def __init__(self):
		QtCore.QThread.__init__(self)

	def __del__(self):
		self.wait()

	def run(self):
		time.sleep(1)
		self.rpcserver = ServerProxy("http://127.0.0.1:20020/")
		print("conn")

	def stop(self):
		try:
			self.rpcserver.jsmod.stop()
		except:
			pass

	def startserver(self):
		print(self.rpcserver.jsmod.start("ok"))

	def execute(self, commmand):
		return self.rpcserver.jsmod.execute(commmand)

	def getstatus(self):
		return (self.rpcserver.jsmod.get_status())

	def cpu(self):
		return self.rpcserver.jsmod.cpu()

	def ram(self):
		return self.rpcserver.ram()

	def plugin_load(self, plugin):
		self.rpcserver.plugin_load(plugin)

	def plugin_unload(self, plugin):
		self.rpcserver.plugin_unload(plugin)

	def plugin_reload(self, plugin):
		self.rpcserver.plugin_reload(plugin)

	def plugin_list(self):
		enabled = []
		for file in glob.glob(os.getcwd() + "/plugins/*.jar"):
			enabled.append(file)

		disabled = []
		for file in glob.glob(os.getcwd() + "/plugins/*.disabled"):
			disabled.append(file)

	def plugin_disable(self, plugin):
		self.rpcserver.jsmod.plugin_unload(plugin)
		os.rename(os.getcwd() + "/plugins/" + plugin + ".jar", os.getcwd() + "/plugins/" + plugin + ".disabled")

	def plugin_enable(self, plugin):
		self.rpcserver.jsmod.plugin_load(plugin)
		os.rename(os.getcwd() + "/plugins/" + plugin + ".disabled", os.getcwd() + "/plugins/" + plugin + ".jar")

	def plugin_info(self, plugin):
		print(plugin)
		return self.rpcserver.jsmod.plugin_info(plugin)


app = QApplication(sys.argv)
MainWindow = QMainWindow()
ui = Ui_JSmod2Manager()
ui.setupUi(MainWindow)
MainWindow.show()
MainWindow.setFixedSize(600, 411)
MainWindow.setWindowTitle("JSmod2启动器")
app.exit(app.exec_())
