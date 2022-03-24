function renderSize(filesize){
    if(null==filesize||filesize===''){
        return "0 B";
    }
    let unitArr = ["B","KB","MB","GB"];
    let index=0;
    let srcSize = parseFloat(filesize);
    index = Math.floor(Math.log(srcSize)/Math.log(1024));
    let size = srcSize/Math.pow(1024,index);
    size=size.toFixed(1);//保留的小数位数
    return size+' '+unitArr[index];
}
console.log(renderSize(61569))