package optimizationProblem;

import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{

	private int[] gene; //��������
	private int fitness; //��Ⱦɫ�����Ӧ��
	
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


	//����һ��Ⱦɫ�����Ӧ�� ����Խ��,��Ӧ��Խ��
  	public int computeFitness() {
  	    int dis = (int)GeneticAlgorithm.distance[gene[gene.length - 1]][gene[0]];
  	    for(int i = 0; i < gene.length - 1; i++)
  	        dis += GeneticAlgorithm.distance[gene[i]][gene[i + 1]];
  	    return dis;
  	}
  	
    //���� ���ر�����Ⱦɫ��
    public Chromosome Mutation() {
        //���������������
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
    
    //�ø����Ƿ��ܴ��
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