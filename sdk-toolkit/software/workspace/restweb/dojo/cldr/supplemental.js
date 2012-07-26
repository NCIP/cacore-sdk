/*
	Copyright (c) 2004-2011, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/cldr/supplemental",["../_base/lang","../i18n"],function(_1,_2){var _3=_1.getObject("dojo.cldr.supplemental",true);_3.getFirstDayOfWeek=function(_4){var _5={mv:5,ae:6,af:6,bh:6,dj:6,dz:6,eg:6,er:6,et:6,iq:6,ir:6,jo:6,ke:6,kw:6,ly:6,ma:6,om:6,qa:6,sa:6,sd:6,so:6,sy:6,tn:6,ye:6,ar:0,as:0,az:0,bw:0,ca:0,cn:0,fo:0,ge:0,gl:0,gu:0,hk:0,il:0,"in":0,jm:0,jp:0,kg:0,kr:0,la:0,mh:0,mn:0,mo:0,mp:0,mt:0,nz:0,ph:0,pk:0,sg:0,th:0,tt:0,tw:0,um:0,us:0,uz:0,vi:0,zw:0};var _6=_3._region(_4);var _7=_5[_6];return (_7===undefined)?1:_7;};_3._region=function(_8){_8=_2.normalizeLocale(_8);var _9=_8.split("-");var _a=_9[1];if(!_a){_a={de:"de",en:"us",es:"es",fi:"fi",fr:"fr",he:"il",hu:"hu",it:"it",ja:"jp",ko:"kr",nl:"nl",pt:"br",sv:"se",zh:"cn"}[_9[0]];}else{if(_a.length==4){_a=_9[2];}}return _a;};_3.getWeekend=function(_b){var _c={"in":0,af:4,dz:4,ir:4,om:4,sa:4,ye:4,ae:5,bh:5,eg:5,il:5,iq:5,jo:5,kw:5,ly:5,ma:5,qa:5,sd:5,sy:5,tn:5};var _d={af:5,dz:5,ir:5,om:5,sa:5,ye:5,ae:6,bh:5,eg:6,il:6,iq:6,jo:6,kw:6,ly:6,ma:6,qa:6,sd:6,sy:6,tn:6};var _e=_3._region(_b);var _f=_c[_e];var end=_d[_e];if(_f===undefined){_f=6;}if(end===undefined){end=0;}return {start:_f,end:end};};return _3;});