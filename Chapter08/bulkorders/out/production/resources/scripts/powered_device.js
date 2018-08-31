function isInconsistent(order){
    isConsistent = false
    items = order.getItems()
    for( i in items ){
    item = items[i]
    print( item )
        if( item.getProductId() == "126" ||
            item.getProductId() == "127" ||
            item.getProductId() == "128"  ){
            isConsistent = true
            }
    }
    return ! isConsistent
}
