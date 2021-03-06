package optimizationProblem;

import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{

	private int[] gene; //基因序列
	private int fitness; //该染色体的适应度
	
	public Chromosome(int[] gene){
		this.gene = gene.clone();
		this.fitness = computeFitness();
	}
	
	public Chromosome(){
	
	}
		
	public int[] getGene() {
		return gene;
	}

	public void setGene(int[] gene) {
		this.gene = gene;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}


	//计算一条染色体的适应度 距离越短,适应度越高
  	public int computeFitness() {
  	    int dis = (int)GeneticAlgorithm.distance[gene[gene.length - 1]][gene[0]];
  	    for(int i = 0; i < gene.length - 1; i++)
  	        dis += GeneticAlgorithm.distance[gene[i]][gene[i + 1]];
  	    return dis;
  	}
  	
    //变异 返回变异后的染色体
    public Chromosome Mutation() {
        //随机交换两个基因
        int t1 = (int)(Math.random()*(gene.length - 1));
        int t2;
        do{
        	t2 = (int)(Math.random()*(gene.length - 1));
        }while(t1 == t2);
        
       
        //swap(gene[t1],gene[t2]);
        swapGene(gene,t1,t2);
        Chromosome Chrom = new Chromosome(gene);
       
        return Chrom;
    }
    
    //该个体是否能存活
    public boolean Alive() {
    	if(gene[0] != GeneticAlgorithm.beginIndex)
    		return false;
        boolean[] flag = new boolean[gene.length];
        for(int i = 0; i < gene.length; i++)
            if(flag[gene[i]])
                return false;
            else
                flag[gene[i]] = true;
        return true;
    }
    
     
    private void swapGene( int[] gene, int index1, int index2){
    	int temp = gene[index1];
    	gene[index1] = gene[index2];
    	gene[index2] = temp;
    }

	@Override
	public int compareTo(Chromosome compareChro) {
		if(this.equals(compareChro))
			return 0;
		if(fitness == compareChro.getFitness()){
			for (int i = 0;i <gene.length;i++){
				if(gene[i] != compareChro.getGene()[i])
					return gene[i] - compareChro.getGene()[i];
			}
		}
		return fitness - compareChro.getFitness();
	}
	
	
	public void print(){
		for(int i = 0; i < gene.length; i++) {
			System.out.print(gene[i]+" ");
        }
		System.out.println("; Fitness: "+fitness+". ");
	}

	
}
