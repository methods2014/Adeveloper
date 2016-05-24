个人中心功能:
 * 检查更新
 * 清理缓存
 * 意见反馈
 * 关于
 *  优化
 * (分享)
 * (浏览足迹)
 * (主题、夜间模式)
 * (地理位置)
 */
友盟统计开发文档
 http://dev.umeng.com/analytics/android-doc/integration?spm=0.0.0.0.mlZ9lT

properties 中文转换工具
http://www.javawind.net/tools/native2ascii.jsp?action=transform
读取文件目录的方式
1.assets文件夹下，建立一个文件夹design_lovelion
2.其中包含
       *.html
       /icon/*.png
       list.properties
这三类文件html的文件名和icon的文件名相同，已方便读取
3.读取文件的时候，首先读取list.properties中的item的值，然后根据“，”分隔符，分隔出共有多少个文件
4.再从list.properties中读取出每个文件的标题，展示在列表中的标题
