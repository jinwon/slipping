function load_tree()
{
	// Tree Navigation
	var tNav = jQuery('.tNav');
	var tNavPlus = '\<button type=\"button\" class=\"tNavToggle plus\"\>+\<\/button\>';
	var tNavMinus = '\<button type=\"button\" class=\"tNavToggle minus\"\>-\<\/button\>';
	tNav.find('li>ul').css('display','none');
	tNav.find('ul>li:last-child').addClass('last');
	tNav.find('li>ul:hidden').parent('li').prepend(tNavPlus);
	tNav.find('li>ul:visible').parent('li').prepend(tNavMinus);
	tNav.find('li.active').addClass('open').parents('li').addClass('open');
	tNav.find('li.open').parents('li').addClass('open');
	tNav.find('li.open>.tNavToggle').text('-').removeClass('plus').addClass('minus');
	tNav.find('li.open>ul').slideDown(100);
	jQuery('.tNav .tNavToggle').click(function(){
		t = jQuery(this);
		t.parent('li').toggleClass('open');
		if(t.parent('li').hasClass('open')){
			t.text('-').removeClass('plus').addClass('minus');
			t.parent('li').find('>ul').slideDown(100);
		} else {
			t.text('+').removeClass('minus').addClass('plus');
			t.parent('li').find('>ul').slideUp(100);
		}
		return false;
	});
	jQuery('.tNav a[href=#]').click(function(){
		t = jQuery(this);
		t.parent('li').toggleClass('open');
		if(t.parent('li').hasClass('open')){
			t.prev('button.tNavToggle').text('-').removeClass('plus').addClass('minus');
			t.parent('li').find('>ul').slideDown(100);
		} else {
			t.prev('button.tNavToggle').text('+').removeClass('minus').addClass('plus');
			t.parent('li').find('>ul').slideUp(100);
		}
		return false;
	});
}

function load_mbox_tree() 
{
	// Tree Navigation
	var tNav = jQuery('.tNavMbox');
	var tNavPlus = '\<button type=\"button\" class=\"tNavToggle plus\"\>+\<\/button\>';
	var tNavMinus = '\<button type=\"button\" class=\"tNavToggle minus\"\>-\<\/button\>';
	tNav.find('li>ul').css('display','none');
	tNav.find('ul>li:last-child').addClass('last');
	tNav.find('li>ul:hidden').parent('li').prepend(tNavPlus);
	tNav.find('li>ul:visible').parent('li').prepend(tNavMinus);
	tNav.find('li.active').addClass('open').parents('li').addClass('open');
	tNav.find('li.open').parents('li').addClass('open');
	tNav.find('li.open>.tNavToggle').text('-').removeClass('plus').addClass('minus');
	tNav.find('li.open>ul').slideDown(100);
	jQuery('.tNavMbox .tNavToggle').click(function(){
		t = jQuery(this);
		t.parent('li').toggleClass('open');
		if(t.parent('li').hasClass('open')){
			t.text('-').removeClass('plus').addClass('minus');
			t.parent('li').find('>ul').slideDown("fast", save_tree_stat);
		} else {
			t.text('+').removeClass('minus').addClass('plus');
			t.parent('li').find('>ul').slideUp("fast", save_tree_stat);
		}
		return false;
	});
	jQuery('.tNavMbox a[href=#]').click(function(){
		t = jQuery(this);
		t.parent('li').toggleClass('open');
		if(t.parent('li').hasClass('open')){
			t.prev('button.tNavToggle').text('-').removeClass('plus').addClass('minus');
			t.parent('li').find('>ul').slideDown("fast");
		} else {
			t.prev('button.tNavToggle').text('+').removeClass('minus').addClass('plus');
			t.parent('li').find('>ul').slideUp("fast");
		}
		return false;
	});
}

function save_tree_stat()
{
	var inbox_open = "";
	var tempbox_open = "";
	var sendbox_open = "";
	var trash_open = "";
	var junk_open = "";
	var private_open = "";

	if (jQuery('#tree_mailbox_inbox').hasClass('open'))
	{
		inbox_open = "open";
	}

	if (jQuery('#tree_mailbox_tempbox').hasClass('open'))
	{
		tempbox_open = "open";
	}

	if (jQuery('#tree_mailbox_sendbox').hasClass('open'))
	{
		sendbox_open = "open";
	}

	if (jQuery('#tree_mailbox_trash').hasClass('open'))
	{
		trash_open = "open";
	}

	if (jQuery('#tree_mailbox_junk').hasClass('open'))
	{
		junk_open = "open";
	}

	if (jQuery('#tree_private_mailbox').hasClass('open'))
	{
		private_open = "open";
	}

	jQuery.ajax({
		type: "POST",
		url: "/index.php/mailboxes/my_mailbox_tree_open",
		dataType: "text",
		data:{
			"inbox_open": inbox_open,
			"tempbox_open": tempbox_open,
			"sendbox_open": sendbox_open,
			"trash_open": trash_open,
			"junk_open": junk_open,
			"private_open": private_open
		},
		success:function(data, status) {
		}
	});
}