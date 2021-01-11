package com.example.demo.designMode.builder;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:07
 * @Version:1.0
 * @Description:
 */
public class CreateMapBuilder implements Build{
    private Map map = new Map();
    @Override
    public Map getMap() {
        return map;
    }
    @Override
    public CreateMapBuilder  buildDefenseTower(DefenseTower defenseTower) {
        map.setDefenseTower(defenseTower);
        return this;
    }
    @Override
    public CreateMapBuilder  buildRoad(Road road) {
        map.setRoad(road);
        return this;
    }
    @Override
    public CreateMapBuilder buildTree(Tree tree) {
        map.setTree(tree);
        return this;
    }
    @Override
    public CreateMapBuilder buildMonster(Monster monster) {
        map.setMonster(monster);
        return this;
    }
    @Override
    public CreateMapBuilder buildMusic(String music) {
        map.setMusic(music);
        return this;
    }
    @Override
    public CreateMapBuilder buildPx(String px) {
        map.setPx(px);
        return this;
    }
}