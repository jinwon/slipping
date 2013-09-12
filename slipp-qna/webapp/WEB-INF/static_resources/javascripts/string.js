    String.prototype.trim = function() {
        return (this.replace(/^\s+|\s+$/g, ""));
    }

    String.prototype.ltrim = function() {
        return this.replace(/^\s+/, "");
    }

    String.prototype.rtrim = function() {
        return this.replace(/\s+$/, "");
    }

    function isDomain(str)
    {
        var len = str.length;


        for(var i = 0; i < len; i++) {
            ch = str.charAt(i);
            if ((ch < '0' || ch > '9') && (ch < 'A' || ch > 'Z') && (ch < 'a' || ch > 'z') && ch != '-' && ch != '_' && ch != '\.') {
                return (false);
            }
        }

        return (true);
    }

    function isId(str)
    {
        var len = str.length;


        if (len < 2 || len > 20) {
            return (-1);
        }

        for(var i = 0; i < len; i++) {
            ch = str.charAt(i);

            if ((ch < '0' || ch > '9') && (ch < 'A' || ch > 'Z') && (ch < 'a' || ch > 'z') && ch != '-' && ch != '_' && ch != '.') {
                return (-2);
            }
        }

        return (true);
    }

    function isEmail(str)
    {
        if (str.indexOf('@') < 0) {
            return (false);
        }

        str_array = str.split('@');

        if (isId(str_array[0]) < 0) {
            return (false);
        }

        if (!isDomain(str_array[1])) {
            return (false);
        }

        return (true);
    }



    function check_digit(e)
    {
        if ((e.keyCode < 48) || (e.keyCode > 57)) {
            e.returnValue = false;
        }
    }



    // ?±?≫ ???, ±灌 u?

    function Check_StrLength( sTargetStr )
    {
        var sTmpStr, sTmpChar;
        var nOriginLen = 0;
        var nStrLength = 0;


        sTmpStr = new String(sTargetStr);
        nOriginLen = sTmpStr.length;

        for ( var i=0 ; i < nOriginLen ; i++ ) {
            sTmpChar = sTmpStr.charAt(i);

            if (escape(sTmpChar).length > 4) {
                nStrLength += 2;
            } else if (sTmpChar!='\r') {
                nStrLength ++;
            }
        }


        // T·μ?½º?¸μG ¹??® ¸®?

        return nStrLength;
    }



    var Url = {
        // public method for url encoding
        encode : function (string) {
            return escape(this._utf8_encode(string));
        },

        // public method for url decoding
        decode : function (string) {
            return (this._utf8_decode(unescape(string)));
        },

        // private method for UTF-8 encoding
        _utf8_encode : function (string) {
            string = string.replace(/\r\n/g,"\n");
            var utf_text = "";


            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);


                if (c < 128) {
                    utf_text += String.fromCharCode(c);
                }
                else if ((c > 127) && (c < 2048)) {
                    utf_text += String.fromCharCode((c >> 6) | 192);
                    utf_text += String.fromCharCode((c & 63) | 128);
                }
                else {
                    utf_text += String.fromCharCode((c >> 12) | 224);
                    utf_text += String.fromCharCode(((c >> 6) & 63) | 128);
                    utf_text += String.fromCharCode((c & 63) | 128);
                }

            }

            return (utf_text);
        },

        // private method for UTF-8 decoding
        _utf8_decode : function (utf_text) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;


            while (i < utf_text.length) {
                c = utf_text.charCodeAt(i);

                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                }
                else if ((c > 191) && (c < 224)) {
                    c2 = utf_text.charCodeAt(i+1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                }
                else {
                    c2 = utf_text.charCodeAt(i+1);
                    c3 = utf_text.charCodeAt(i+2);

                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));

                    i += 3;
                }

            }

            return (string);
        }
    }


    function containsChars(input,chars)
    {
        for (var inx = 0; inx < input.length; inx++) {

            if (chars.indexOf(input.charAt(inx)) != -1) {
                return true;
            }
        }

        return false;
    }


    var Base64 = { // private property
        _keyStr_enc: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*/=",
        _keyStr_dec: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
        // public method for encoding
        encode: function(input) {
            var output = "";
            var chr1,
            chr2,
            chr3,
            enc1,
            enc2,
            enc3,
            enc4;
            var i = 0;
            input = Base64._utf8_encode(input);
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output = output + this._keyStr_enc.charAt(enc1) + this._keyStr_enc.charAt(enc2) + this._keyStr_enc.charAt(enc3) + this._keyStr_enc.charAt(enc4);
            }
            return output;
        },
        // public method for decoding
        decode: function(input) {
            var output = "";
            var chr1,
            chr2,
            chr3;
            var enc1,
            enc2,
            enc3,
            enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while (i < input.length) {
                enc1 = this._keyStr_dec.indexOf(input.charAt(i++));
                enc2 = this._keyStr_dec.indexOf(input.charAt(i++));
                enc3 = this._keyStr_dec.indexOf(input.charAt(i++));
                enc4 = this._keyStr_dec.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = Base64._utf8_decode(output);
            return output;
        },
        // private method for UTF-8 encoding
        _utf8_encode: function(string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }
            }
            return utftext;
        },
        // private method for UTF-8 decoding
        _utf8_decode: function(utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while (i < utftext.length) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    };



    sprintf_wrapper = {
    	init : function () {
    		if (typeof arguments == 'undefined') { return null; }
    		if (arguments.length < 1) { return null; }
    		if (typeof arguments[0] != 'string') { return null; }
    		if (typeof RegExp == 'undefined') { return null; }

    		var string = arguments[0];
    		var exp = new RegExp(/(%([%]|(\-)?(\+|\x20)?(0)?(\d+)?(\.(\d)?)?([bcdfosxX])))/g);
    		var matches = new Array();
    		var strings = new Array();
    		var convCount = 0;
    		var stringPosStart = 0;
    		var stringPosEnd = 0;
    		var matchPosEnd = 0;
    		var newString = '';
    		var match = null;

    		while (match = exp.exec(string)) {
    			if (match[9]) { convCount += 1; }

    			stringPosStart = matchPosEnd;
    			stringPosEnd = exp.lastIndex - match[0].length;
    			strings[strings.length] = string.substring(stringPosStart, stringPosEnd);

    			matchPosEnd = exp.lastIndex;
    			matches[matches.length] = {
    				match: match[0],
    				left: match[3] ? true : false,
    				sign: match[4] || '',
    				pad: match[5] || ' ',
    				min: match[6] || 0,
    				precision: match[8],
    				code: match[9] || '%',
    				negative: parseInt(arguments[convCount]) < 0 ? true : false,
    				argument: String(arguments[convCount])
    			};
    		}
    		strings[strings.length] = string.substring(matchPosEnd);

    		if (matches.length == 0) { return string; }
    		if ((arguments.length - 1) < convCount) { return null; }

    		var code = null;
    		var match = null;
    		var i = null;

    		for (i=0; i<matches.length; i++) {

    			if (matches[i].code == '%') { substitution = '%' }
    			else if (matches[i].code == 'b') {
    				matches[i].argument = String(Math.abs(parseInt(matches[i].argument)).toString(2));
    				substitution = sprintf_wrapper.convert(matches[i], true);
    			}
    			else if (matches[i].code == 'c') {
    				matches[i].argument = String(String.fromCharCode(parseInt(Math.abs(parseInt(matches[i].argument)))));
    				substitution = sprintf_wrapper.convert(matches[i], true);
    			}
    			else if (matches[i].code == 'd') {
    				matches[i].argument = String(Math.abs(parseInt(matches[i].argument)));
    				substitution = sprintf_wrapper.convert(matches[i]);
    			}
    			else if (matches[i].code == 'f') {
    				matches[i].argument = String(Math.abs(parseFloat(matches[i].argument)).toFixed(matches[i].precision ? matches[i].precision : 6));
    				substitution = sprintf_wrapper.convert(matches[i]);
    			}
    			else if (matches[i].code == 'o') {
    				matches[i].argument = String(Math.abs(parseInt(matches[i].argument)).toString(8));
    				substitution = sprintf_wrapper.convert(matches[i]);
    			}
    			else if (matches[i].code == 's') {
    				matches[i].argument = matches[i].argument.substring(0, matches[i].precision ? matches[i].precision : matches[i].argument.length)
    				substitution = sprintf_wrapper.convert(matches[i], true);
    			}
    			else if (matches[i].code == 'x') {
    				matches[i].argument = String(Math.abs(parseInt(matches[i].argument)).toString(16));
    				substitution = sprintf_wrapper.convert(matches[i]);
    			}
    			else if (matches[i].code == 'X') {
    				matches[i].argument = String(Math.abs(parseInt(matches[i].argument)).toString(16));
    				substitution = sprintf_wrapper.convert(matches[i]).toUpperCase();
    			}
    			else {
    				substitution = matches[i].match;
    			}

    			newString += strings[i];
    			newString += substitution;

    		}
    		newString += strings[i];

    		return newString;

    	},

    	convert : function(match, nosign){
    		if (nosign) {
    			match.sign = '';
    		} else {
    			match.sign = match.negative ? '-' : match.sign;
    		}
    		var l = match.min - match.argument.length + 1 - match.sign.length;
    		var pad = new Array(l < 0 ? 0 : l).join(match.pad);
    		if (!match.left) {
    			if (match.pad == '0' || nosign) {
    				return match.sign + pad + match.argument;
    			} else {
    				return pad + match.sign + match.argument;
    			}
    		} else {
    			if (match.pad == '0' || nosign) {
    				return match.sign + match.argument + pad.replace(/0/g, ' ');
    			} else {
    				return match.sign + match.argument + pad;
    			}
    		}
    	}
    }

    sprintf = sprintf_wrapper.init;


    function printf()
    {
        // http://kevin.vanzonneveld.net
        // +   original by: Ash Searle (http://hexmen.com/blog/)
        // +   improved by: Michael White (http://crestidg.com)
        // -    depends on: sprintf
        // *     example 1: printf("%01.2f", 123.1);
        // *     returns 1: 6

        var ret = sprintf.apply(this, arguments);
        document.write(ret);
        return ret.length;
    }


    function strip_tags(str, allowed_tags) {
        // http://kevin.vanzonneveld.net
        // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
        // +   improved by: Luke Godfrey
        // +      input by: Pul
        // +   bugfixed by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
        // +   bugfixed by: Onno Marsman
        // +      input by: Alex
        // +   bugfixed by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
        // *     example 1: strip_tags('<p>Kevin</p> <br /><b>van</b> <i>Zonneveld</i>', '<i><b>');
        // *     returns 1: 'Kevin <b>van</b> <i>Zonneveld</i>'
        // *     example 2: strip_tags('<p>Kevin <img src="someimage.png" onmouseover="someFunction()">van <i>Zonneveld</i></p>', '<p>');
        // *     returns 2: '<p>Kevin van Zonneveld</p>'
        // *     example 3: strip_tags("<a href='http://kevin.vanzonneveld.net'>Kevin van Zonneveld</a>", "<a>");
        // *     returns 3: '<a href='http://kevin.vanzonneveld.net'>Kevin van Zonneveld</a>'

        var key = '', tag = '', allowed = false;
        var matches = allowed_array = [];
        var allowed_keys = {};

        var replacer = function(search, replace, str) {
            return str.split(search).join(replace);
        };

        // Build allowes tags associative array
        if (allowed_tags) {
            allowed_array = allowed_tags.match(/([a-zA-Z]+)/gi);
        }

        str += '';

        // Match tags
        matches = str.match(/(<\/?[^>]+>)/gi);

        // Go through all HTML tags
        for (key in matches) {
            if (isNaN(key)) {
                // IE7 Hack
                continue;
            }

            // Save HTML tag
            html = matches[key].toString();

            // Is tag not in allowed list? Remove from str!
            allowed = false;

            // Go through all allowed tags
            for (k in allowed_array) {
                // Init
                allowed_tag = allowed_array[k];
                i = -1;

                if (i != 0) { i = html.toLowerCase().indexOf('<'+allowed_tag+'>');}
                if (i != 0) { i = html.toLowerCase().indexOf('<'+allowed_tag+' ');}
                if (i != 0) { i = html.toLowerCase().indexOf('</'+allowed_tag)   ;}

                // Determine
                if (i == 0) {
                    allowed = true;
                    break;
                }
            }

            if (!allowed) {
                str = replacer(html, "", str); // Custom replace. No regexing
            }
        }

        return str;
    }



    function nl2br (str, is_xhtml) {
        // http://kevin.vanzonneveld.net
        // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
        // +   improved by: Philip Peterson
        // +   improved by: Onno Marsman
        // +   improved by: Atli ¨­or
        // +   bugfixed by: Onno Marsman
        // *     example 1: nl2br('Kevin\nvan\nZonneveld');
        // *     returns 1: 'Kevin<br />\nvan<br />\nZonneveld'
        // *     example 2: nl2br("\nOne\nTwo\n\nThree\n", false);
        // *     returns 2: '<br>\nOne<br>\nTwo<br>\n<br>\nThree<br>\n'
        // *     example 3: nl2br("\nOne\nTwo\n\nThree\n", true);
        // *     returns 3: '<br />\nOne<br />\nTwo<br />\n<br />\nThree<br />\n'

        breakTag = '<br />';
        if (typeof is_xhtml != 'undefined' && !is_xhtml) {
            breakTag = '<br>';
        }

        return (str + '').replace(/([^>]?)\n/g, '$1'+ breakTag +'\n');
    }


    function preg_replace(search, replace, str)
    {
        var len = search.length;


        for(var i = 0; i < len; i++) {
                re = new RegExp(search[i], "ig");
                str = str.replace(re, typeof replace == 'string' ? replace : (replace[i] ? replace[i] : replace[0]));
        }

        return str;
    }


    function get_text_plain_email_content(str)
    {
        var text_line = "";
        var prev_text_line = "";
        var result_text = "";


        if (str.length == 0) {
            result_text = "\n";
        }


        for (i = 0; i < str.length; i++) {
            if (str.charAt(i) == '\n') {
                text_line = text_line.trim();
                if (prev_text_line.length == 0 && text_line.length == 0) {
                    result_text += '\n';
                }
                else {
                    /*
                    if (text_line.indexOf("Subject: ") >= 0) {
                        result_text += text_line + '\n';
                        result_text += '\n';
                    }
                    else {
                        if (text_line.indexOf("----- Original Message -----") >= 0) {
                            result_text += "\n\n" + text_line + '\n';
                        }
                        else {
                            result_text += text_line;
                        }
                    }
                    */

                    result_text += text_line + '\n';
                }

                text_line = "";
                prev_text_line = text_line;
            }
            else {
                text_line = text_line + str.charAt(i);
            }
        }

        if (text_line.length > 0) {
            result_text += text_line;
        }

        result_text += '\n';

        return (result_text);
    }
