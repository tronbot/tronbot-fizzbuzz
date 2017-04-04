package hackerrank

/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 30, 2017
 */

class MenuItem implements Comparable<MenuItem>{
	public MenuItem(String name, Integer price) {
		super();
		this.name = name
		this.price = price
	}

	String name
	Integer price

	public int compareTo(MenuItem o){
		if(!price||!o){
			return -1
		}
		return price.compareTo(o.price)
	}

	public String toString(){
		return "${name}:${price}"
	}
}


List<MenuItem> makeMenu(Map<String, Integer> menuData, Integer limit){
	List<MenuItem> menu = new ArrayList()
	for(String name : menuData.keySet()){
		Integer price = menuData.get(name)
		if(price <= limit){
			menu.add(new MenuItem(name, menuData.get(name)))
		}
	}
	Collections.sort(menu)
	return menu
}


List<List<MenuItem>> findChoices(Map<String, Integer> menuData, Integer money){
	List<MenuItem> menu = makeMenu(menuData, money)
	List<List<MenuItem>> choices = new ArrayList()
	Integer idx  = 0
	menu.findAll{ item ->
		if(item.price <= money/2){
			Integer complement = money - item.price
			List<MenuItem> complementChocies = findItemBelowPrice(menu, idx+1, menu.size(),complement)
			complementChocies.each { ot ->
				List<MenuItem> choice = new ArrayList()
				choice.add(item)
				choice.add(ot)
				choices.add(choice)
			}
			idx++
		}else{
			return false
		}
	}
	return choices
}


List<MenuItem> findItemBelowPrice(List<MenuItem> menu, Integer start, Integer end,Integer maxPrice){
	menu.subList(start, end).findAll{
		return it.price<=maxPrice
	}
}

Map<String, Integer> menuData =
		[Strawberry:2,
			BlueBerry:7,
			Nutella:13,
			Vanilla:5,
			Banana:4,
			Bubblegum:13,
			Chocolate:5]

Integer money = 10
println menuData
List<List<MenuItem>> choices = findChoices(menuData, 15)
println choices