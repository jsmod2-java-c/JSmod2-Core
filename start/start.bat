:: Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
:: It needs to rely on smod2 and proxy. jsmod2 is an open source
:: free plugin that is released under the GNU license. Please read
:: the GNU open source license before using the software. To understand
:: the appropriateness, if infringement, will be handled in accordance
:: with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>

::python版语言环境、配置自动安装批处理文件


::初始化批处理文件
CLS
@ECHO OFF
ECHO.

::检测操作系统是否为windows32位操作系统，如果是，继续安装，如果不是，提示并退出安装
::检测是否已经安装python2.76，如果是，继续安装，安装模式为静默模式；如果不是，提示并退出安装
::默认安装文件为C:\PYTHON27
ECHO Start to install python2.76 win32......
cd\
cd testsetup
start /wait c:\testsetup\python-2.7.6.msi /qn
ECHO install python2.76 successfully......

::检测系统变量PATH，是否已经配置python参数，c:\python27和c:\python27\scripts，如果已经配置，跳过；如果没有，继续执行
::检查path中有没有c:\python27(有就跳到run，没有就接着执行) 
echo start to set python sys path....... 
echo %path%|findstr /i "c:\python27"&&(goto run)  

::先添加，防止没有时修改出错
echo check path....  
::wmic ENVIRONMENT create name="path",VariableValue="%path%c:\python27;"  
::再修改，防止已有时添加出错 
echo check python path...... 
wmic ENVIRONMENT where "name='path' and username='<system>'" set VariableValue="%path%c:\python27;"
::再即时应用  
echo aplly path......
set path=%path%c:\python27;


:run
::开始安装setuptools

::替换已经准备好的mimetypes.py文件，防止安装setuptools的时候出现编码错误  
ECHO replace mimetypes.py....
replace "c:\testsetup\mimetypes.py" "C:\Python27\Lib"
if errorlevel 0 echo 执行成功!

::安装setuptools 
ECHO Start to install SETUPTOOLS......
cd c:\testsetup
cd setuptools
python setup.py install
if errorlevel 0 echo 执行成功!

echo start to set python scripts path......
::检查path中有没有c:\python27\scripts(有就跳到run，没有就接着执行) 
echo start to set python sys path....... 
echo %path%|findstr /i "c:\python27\scripts"&&(goto run)  

::先添加，防止没有时修改出错
echo check path....  
::wmic ENVIRONMENT create name="path",VariableValue="c:\python27\scripts;%path%"  
::再修改，防止已有时添加出错 
echo check python path...... 
wmic ENVIRONMENT where "name='path' and username='<system>'" set VariableValue="%path%c:\python27\scripts;"
::再即时应用  
echo aplly path......
set path=%path%c:\python27\scripts;

:run2

::安装rsa库
ECHO Start to easy_install rsa......
easy_install rsa
if errorlevel 0 echo 执行成功!

".\start.py"
path

pause
exit


