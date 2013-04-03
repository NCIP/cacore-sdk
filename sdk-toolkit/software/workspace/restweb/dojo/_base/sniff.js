/*
	Copyright (c) 2004-2011, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/_base/sniff",["./kernel","../sniff"],function(_1,_2){if(!1){return _2;}_1.isBrowser=true,_1._name="browser";_1.isOpera=_2("opera");_1.isAIR=_2("air");_1.isKhtml=_2("khtml");_1.isWebKit=_2("webkit");_1.isChrome=_2("chrome");_1.isMac=_2("mac");_1.isSafari=_2("safari");_1.isMozilla=_1.isMoz=_2("mozilla");_1.isIE=_2("ie");_1.isFF=_2("ff");_1.isQuirks=_2("quirks");_1.isIos=_2("ios");_1.isAndroid=_2("android");_1.locale=_1.locale||(_1.isIE?navigator.userLanguage:navigator.language).toLowerCase();return _2;});