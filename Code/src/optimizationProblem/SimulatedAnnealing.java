package optimizationProblem;

/**
 * TSP: Travelling Salesman Problem
 * 
 * ������һ������������Ҫ�ݷ�n������
 * ������Ҫѡ��һ��·���ܹ��ݷõ����еĳ��У���ÿ������ֻ�ܱ��ݷ�һ�Σ����ջص����
 * Ŀ�꣺��õ�·��·��Ϊ����·������֮�е���Сֵ
 * �㷨������ �ӵ�ǰ�Ľڵ㿪ʼ������Χ���ھӽڵ��ֵ���бȽϣ���һ���ĸ���������һ���ȵ�ǰ��Ҫ��Ľ⣺
 * 		     �����ǰ�ڵ�����ھӽڵ㣬��һ�������滻��ǰ�ڵ㣬���ھӽڵ���Ϊ���ֵ(��ɽ����ߵ�)��
 * 		    ��֮������ߵ��ھӽڵ������滻��ǰ�ڵ㣻
 * 		    ���ѭ��ֱ���ﵽ��ߵ�
 * @author Fan
 *
 */
public class SimulatedAnnealing {
	// ����������
    private double temperature = 10000;
    private double coolingRate = 0.99;
    private double absoluteTemperature = 0.00001;
    
    /**
     * Ѱ�����·��
     * @param currentRoute
     * @return
     */
    public Route findShortestRoute(Route currentRoute) {
        Route adjacentRoute;
        int iterToMaximumCounter = 0;
        String compareRoutes = null;
        while(temperature > absoluteTemperature) {
            adjacentRoute = obtainAdjacentRoute(new Route(currentRoute));
            double deltaDistance = adjacentRoute.getTotalDistance()-currentRoute.getTotalDistance();
            if(deltaDistance <= 0) {
                compareRoutes = "<= (����) - �������� # "+ iterToMaximumCounter;
                currentRoute = new Route(adjacentRoute);
            } else {
            	if(Math.exp(-deltaDistance/temperature)>Math.random()){
            		compareRoutes = "> (�����˻�) - �������� # "+ iterToMaximumCounter;
                    currentRoute = new Route(adjacentRoute);
            	}else{
            		compareRoutes = "> (����) - �������� # " + iterToMaximumCounter;
            	}
            }
            temperature *= coolingRate;
            iterToMaximumCounter++;
            System.out.println("       | " + compareRoutes);
            System.out.print(currentRoute + " |      " + currentRoute.getTotalStringDistance());
        }
        
        System.out.println("       | ���ܵ����Ž�");
        
        return currentRoute;
    }
    
    /**
     * ���������������λ��
     * @param route
     * @return
     */
    public Route obtainAdjacentRoute(Route route) {
        int x1 = 0, x2 = 0;
        while(x1 == x2) {
            x1 = (int) (route.getCities().size() * Math.random());
            x2 = (int) (route.getCities().size() * Math.random());
        }
        
        City city1 = route.getCities().get(x1);
        City city2 = route.getCities().get(x2);
        
        // swap two stochastic cities
        route.getCities().set(x2, city1);
        route.getCities().set(x1, city2);
        return route;
    }
}