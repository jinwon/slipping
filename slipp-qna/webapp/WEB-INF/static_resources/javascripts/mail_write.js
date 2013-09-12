        //주소 자동완성
        var hide_to_list = 1;
        var hide_cc_list = 1;
        var hide_bcc_list = 1;

        function set_autocom_search(num)
        {
            if (num == 1) {
                var com_target = "#m_to";
                var com_div = "#to_addr_list";
            } else if (num == 2) {
                var com_target = "#m_cc";
                var com_div = "#cc_addr_list";
            } else if (num == 3) {
                var com_target = "#m_bcc";
                var com_div = "#bcc_addr_list";
            }

            var com_to = jQuery(com_target).val();
            var v_search = "";
            var array_to = com_to.split(','); 
        
            if (array_to.length > 0)
            {
                v_search = array_to[array_to.length -1];
				v_search = v_search.replace(/^\s+|\s+$/g,"");
            }

            if (v_search != "")
            {
                //ajax로 주소록을 가져옴
                targeturl = "/new_mua/index.php/address/address_autocomplete_list"

                jQuery.ajax({
                    type: "POST",
                    url: targeturl,
                    dataType: "text",
                    data:{
                        "com_to": v_search,
                        "num": num
                     },
                    timeout:3000,
                    success:function(data, status) {
						if (data != "no") {
							jQuery(com_div).empty().append(data);
							jQuery(com_div).css("visibility" , "visible");
						} else {
							jQuery(com_div).css("visibility" , "hidden");
						}
                    }
                });
            }
            else
            {
                jQuery(com_div).css("visibility" , "hidden");
            }
        }

        function set_hidden_to_list(val)
        {
            hide_to_list = val;
        }

        function hidden_to_list()
        {
            if(hide_to_list) { close_to_list(); }
        }

        function close_to_list()
        {
            jQuery("#to_addr_list").css("visibility" , "hidden");
        }

        function add_auto_to(t_addr, num)
        {
            if (num == 1) {
                var to_type = "#m_to";
            }
            else if (num == 2) {
                var to_type = "#m_cc";
            }
            else if (num == 3) {
                var to_type = "#m_bcc";
            }

            var v_m_to = jQuery(to_type).val();
            
            var r_m_to = '';
            var array_to = v_m_to.split(','); 

            for (cnt = 0; cnt < array_to.length;  cnt++)
            {
                if ((cnt + 1)  == array_to.length ) {
                    break;
                }
                else 
                {
                    if (r_m_to == "") {
                        r_m_to =  array_to[cnt];    
                    } else {
                        r_m_to = r_m_to + ', '  + array_to[cnt];
                    }
                }
            } //end for
        
            if (r_m_to == ""){
                r_m_to = t_addr + ",";
            } else {
                r_m_to = r_m_to + ", " + t_addr + ",";
            }

            jQuery(to_type).val(r_m_to);
            jQuery(to_type).focus();
            close_to_list();
        }

        function set_hidden_cc_list(val)
        {
            hide_cc_list = val;
        }

        function hidden_cc_list()
        {
            if(hide_cc_list) { close_cc_list(); }
        }

        function close_cc_list()
        {
            jQuery("#cc_addr_list").css("visibility" , "hidden");
        }

        function set_hidden_bcc_list(val)
        {
            hide_bcc_list = val;
        }

        function hidden_bcc_list()
        {
            if(hide_bcc_list) { close_bcc_list(); }
        }

        function close_bcc_list()
        {
            jQuery("#bcc_addr_list").css("visibility" , "hidden");
        }
