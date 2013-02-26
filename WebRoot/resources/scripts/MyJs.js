function DoAdd(){
	var MainSel = document.getElementById('select1');
	var SlaveSel=document.getElementById('select2');
	var this_sel = null;
	for(var i=0;i<MainSel.options.length;i++){
		this_sel = MainSel.options[i];
		if(this_sel.selected){
			SlaveSel.appendChild(this_sel);
			i--;
		}
	}
}
function DoDel(){
	var SlaveSel = document.getElementById('select2');
	var MainSel=document.getElementById('select1');
	var this_sel = null;
	for(var i=0;i<SlaveSel.options.length;i++){
		this_sel = SlaveSel.options[i];
		if(this_sel.selected){
			MainSel.appendChild(this_sel);
			i--;
		}
	}
}