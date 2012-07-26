/*
	Copyright (c) 2004-2011, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/Stateful",["./_base/declare","./_base/lang","./_base/array"],function(_1,_2,_3){return _1("dojo.Stateful",null,{postscript:function(_4){if(_4){_2.mixin(this,_4);}},get:function(_5){return this[_5];},set:function(_6,_7){if(typeof _6==="object"){for(var x in _6){if(_6.hasOwnProperty(x)){this.set(x,_6[x]);}}return this;}var _8=this[_6];this[_6]=_7;if(this._watchCallbacks){this._watchCallbacks(_6,_8,_7);}return this;},watch:function(_9,_a){var _b=this._watchCallbacks;if(!_b){var _c=this;_b=this._watchCallbacks=function(_d,_e,_f,_10){var _11=function(_12){if(_12){_12=_12.slice();for(var i=0,l=_12.length;i<l;i++){_12[i].call(_c,_d,_e,_f);}}};_11(_b["_"+_d]);if(!_10){_11(_b["*"]);}};}if(!_a&&typeof _9==="function"){_a=_9;_9="*";}else{_9="_"+_9;}var _13=_b[_9];if(typeof _13!=="object"){_13=_b[_9]=[];}_13.push(_a);return {unwatch:function(){_13.splice(_3.indexOf(_13,_a),1);}};}});});