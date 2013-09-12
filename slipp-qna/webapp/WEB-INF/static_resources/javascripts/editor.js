function Load_WordEditor(skin_lang, default_font_type, default_font_size, tabindex)
{
    if (wysiwyg_state == false) {
        var BoraWordObjectDef = "";


        BoraWordObjectDef += "<object id=\"content_BoraX\" tabindex=" + tabindex + " height=\"100%\" width=\"100%\" style=\"position:relative; visibility:visible;\" classid=\"CLSID:38ABEF3A-2FD0-4D78-8DA5-A424010439B2\" codebase=\"/object/BoraTech/BWordAx.cab#version=1.6.6.224\"> ";
        BoraWordObjectDef += "<param name=\"IsForceWeb\" value=\"1\"> ";
        BoraWordObjectDef += "<param name=\"IsMenuBar\" value=\"0\"> ";
        BoraWordObjectDef += "<param name=\"IsToolBar\" value=\"1\"> ";
        BoraWordObjectDef += "<param name=\"IsStyleBar\" value=\"1\"> ";
        BoraWordObjectDef += "<param name=\"IsDrawBar\" value=\"0\"> ";
        BoraWordObjectDef += "<param name=\"IsStatusBar\" value=\"1\"> ";
        BoraWordObjectDef += "<param name=\"Company\" value=\"SDS\"> ";
        BoraWordObjectDef += "</object>";

        document.getElementById("word_editor").innerHTML = BoraWordObjectDef;

        document.content_BoraX.ShowBarIcon(10017, false);
        document.content_BoraX.ShowBarIcon(10371, false);
        // document.content_BoraX.ShowBarIcon(10372, false);
        document.content_BoraX.IsDirty = false;

        switch (skin_lang) {
        case "korean":
            if (default_font_type == "") {
                default_font_type = "굴림";
            }

            if (default_font_size == "") {
                default_font_size = "10";
            }

            document.content_BoraX.SetDefaultFont(default_font_type, default_font_size);
            break;

        case "english":
            if (default_font_type == "") {
                default_font_type = "Arial";
            }

            if (default_font_size == "") {
                default_font_size = "10";
            }

            document.content_BoraX.SetDefaultFont(default_font_type, default_font_size);
            break;
        }

        document.content_BoraX.NewDocument();
        wysiwyg_state = true;
    }
}


function Load_FCKEditor(http_type, http_host, skin_lang, default_font_type, default_font_size, default_style, img_upload_url, startup_focus_flag, content_value)
{
    var sBasePath = "/object/fckeditor/";
    var sSkin = "silver";
    var sSkinPath = sBasePath + 'editor/skins/' + sSkin + '/' ;
    var oFCKeditor = new FCKeditor('FCKeditor1');

    oFCKeditor.BasePath = sBasePath ;
    oFCKeditor.Width = '100%' ;
    oFCKeditor.Height = '450' ;

    oFCKeditor.Config['SmileyPath'] = http_type + http_host + sBasePath + "editor/images/smiley/msn/";

    if (img_upload_url != "") {
        oFCKeditor.Config['ImageUploadURL'] = http_type + http_host + "/object/fckeditor/editor/filemanager/connectors/php/upload.php?Type=Image&" + img_upload_url;
    }

    oFCKeditor.Config['SkinPath'] = sSkinPath ;
    oFCKeditor.Config['PreloadImages'] =
        sSkinPath + 'images/toolbar.start.gif' + ';' +
        sSkinPath + 'images/toolbar.end.gif' + ';' +
        sSkinPath + 'images/toolbar.buttonbg.gif' + ';' +
        sSkinPath + 'images/toolbar.buttonarrow.gif' ;

    // content_value = content_value.replace(/\[LINE_BREAK\]/g, "<p class=\"fcknormal\" style=\"font-family:" + default_font_type + ";font-size:" + default_font_size + "pt\">&nbsp;</p>");
    oFCKeditor.Value = content_value;

    switch (skin_lang) {
    case "korean":
        oFCKeditor.Config['DefaultLanguage'] = 'ko';
        oFCKeditor.Config['FontNames'] = '맑은 고딕;굴림;굴림체;돋움;돋움체;바탕;바탕체;궁서;궁서체;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana';
        break;

    case "english":
        oFCKeditor.Config['DefaultLanguage'] = 'en';
        oFCKeditor.Config['FontNames'] = 'Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana';
        break;
    }

    oFCKeditor.Config['DefaultFontLabel'] = default_font_type;
    oFCKeditor.Config['DefaultFontSizeLabel'] = default_font_size + 'pt';
    oFCKeditor.Config['EditorAreaStyles'] = default_style;
    // oFCKeditor.Config['EnterMode'] = 'div';
    // oFCKeditor.Config['ShiftEnterMode'] = 'div';
    oFCKeditor.Config['StartupFocus'] = startup_focus_flag;
    oFCKeditor.Create();
	return oFCKeditor;	
}


var word_editor_loaded = false;


/*
    < 참고 사항 >

    - document.content_BoraX.HTMLLocalText: 보라워드에서 HTML 로컬 텍스트를 추출할 때 사용
    - document.content_BoraX.PutHtmlSrc(html_data): 보라워드에 HTML 데이터를 삽입할 때 사용
    - FCKeditorAPI.GetInstance('FCKeditor1').SetData(data): FCKeditor에 데이터를 저장할 때 사용
*/

function Init_Editor(form_obj, edit_type, skin_lang, default_font_type, default_font_size)
{
	var decoded_text = form_obj.content_html.value;

	document.getElementById("content_area").innerHTML = decoded_text;

    if (document.getElementById("content_area").innerText == null) {
        var innerText = "";

        if (decoded_text.length > 0) {
            decoded_text = preg_replace(['<style.*?>[\\\s\\\S]*?<\/style>', '<script.*?>[\\\s\\\S]*?<\/script>', '<noscript.*?>[\\\s\\\S]*?<\/noscript>', '<select.*?>[\s\S]*?<\/select>', '<object.*?>[\s\S]*?<\/object>', '<!--[\\\s\\\S]*?-->', ' on[a-zA-Z]{3,16}\\\s?=\\\s?"[\\\s\\\S]*?"'], '', decoded_text);

            innerText = strip_tags(decoded_text);
            innerText = innerText.replace(/\&nbsp;/g, " ");
        }

        form_obj.content_plain.value = innerText;
    }
    else {
        form_obj.content_plain.value = document.getElementById("content_area").innerText;
    }

    form_obj.content_html.value = document.getElementById("content_area").innerHTML;

    if (edit_type != "PLAIN" && edit_type != "DAUM" || edit_type == "HTML") {
        document.getElementById("html_editor").style.display = "block";
        document.getElementById("daum_editor").style.display = "none";
        document.getElementById("plain_editor").style.display = "none";
    }
    else if (edit_type == "DAUM") {
        document.getElementById("html_editor").style.display = "none";
        document.getElementById("daum_editor").style.display = "block";
        document.getElementById("plain_editor").style.display = "none";
    }
    else if (edit_type == "PLAIN") {
        document.getElementById("html_editor").style.display = "none";
        document.getElementById("daum_editor").style.display = "none";
        document.getElementById("plain_editor").style.display = "block";

        // form_obj.content_plain.value = form_obj.content_plain.value.trim();

        if (form_obj.content_plain.value != "") {
            // form_obj.content_plain.value = form_obj.content_plain.value.replace(/\[LINE_BREAK\]/g, "");

            //form_obj.content_plain.value = get_text_plain_email_content(form_obj.content_plain.value);
        }

        if (form_obj.to) {
            if (form_obj.to.value.length > 0) {
                form_obj.plain_editor.focus();
            }
            else {
                form_obj.to.focus();
            }
        }
    }
}


function Change_EditType(form_obj, edit_type, skin_lang, default_font_type, default_font_size, editor_focus_flag)
{
	var decoded_text = form_obj.content_html.value;

    if (edit_type != "PLAIN" && edit_type != "DAUM" || edit_type == "HTML") {
        document.getElementById("html_editor").style.display = "";
        document.getElementById("daum_editor").style.display = "none";
        document.getElementById("plain_editor").style.display = "none";

        if (editor_focus_flag == true) {
//            FCKeditorAPI.GetInstance('FCKeditor1').Focus();
        }
    }
    else if (edit_type == "DAUM") {
        document.getElementById("html_editor").style.display = "none";
        document.getElementById("daum_editor").style.display = "";
        document.getElementById("plain_editor").style.display = "none";

		//다음에디터 추가 부분

    }
    else if (edit_type == "PLAIN") {
        document.getElementById("html_editor").style.display = "none";
        document.getElementById("daum_editor").style.display = "none";
        document.getElementById("plain_editor").style.display = "";

        if (form_obj.content_plain.value != "") {
            // form_obj.content_plain.value = form_obj.content_plain.value.replace(/\[LINE_BREAK\]/g, "\n");
            //form_obj.content_plain.value = get_text_plain_email_content(form_obj.content_plain.value);
        }

        if (editor_focus_flag == true) {
            form_obj.content_plain.focus();
        }

        if (form_obj.to) {
            if (form_obj.to.value.length > 0) {
                form_obj.plain_editor.focus();
            }
            else {
                form_obj.to.focus();
            }
        }
    }
}


function View_BoraWordHTMLSource()
{
    document.content_BoraX.ExecuteMenu(10372);
}


function Change_EditTypeHwp(form_obj, edit_type, skin_lang, default_font_type, default_font_size, editor_focus_flag)
{
	var decoded_text = form_obj.content_html.value;

    if (edit_type == "HTML") {
        document.getElementById("html_editor").style.display = "";
        document.getElementById("hwp_editor").style.display = "none";
        document.getElementById("plain_editor").style.display = "none";

        if (editor_focus_flag == true) {
            FCKeditorAPI.GetInstance('FCKeditor1').Focus();
        }
    }
    else if (edit_type == "HWP") {

		document.getElementById("html_editor").style.display = "none";
        document.getElementById("hwp_editor").style.display = "";
		document.getElementById("plain_editor").style.display = "none";

		//한글인 경우 발송언어는 euc-kr로만 가능하다.
		document.getElementById("mail_charset").value = "euc-kr";
    }
    else if (edit_type == "PLAIN") {
        document.getElementById("html_editor").style.display = "none";
        document.getElementById("hwp_editor").style.display = "none";
        document.getElementById("plain_editor").style.display = "";
    }
}