package com.irar.craftmatter.entity.projectile;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.item.ItemGrenade;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGrenade extends EntityThrowable
{
	private int antimatterRemaining = 0;
	private boolean hasCollided = false;
	private Random r = new Random();
	private ArrayList<BlockPos> toDestroy = new ArrayList<BlockPos>();
	private ArrayList<BlockPos> hasBeenDestroyed = new ArrayList<BlockPos>();
	private int totalAntimatter = 0;
	
    public EntityGrenade(World worldIn)
    {
        super(worldIn);
    }

    public EntityGrenade(World worldIn, EntityLivingBase throwerIn, int amount)
    {
        super(worldIn, throwerIn);
        this.totalAntimatter  = amount;
        this.antimatterRemaining = amount;
    }
    public EntityGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        this.antimatterRemaining = 100;
    }

    public EntityGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesSnowball(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "Snowball");
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.LAVA, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
/*            int i = 10;

            if (result.entityHit instanceof EntityZombie)
            {
                i = 100;
            }else if(result.entityHit instanceof EntityPlayer){
            	i = 0;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
*/      }else {
	        if (!this.world.isRemote)
	        {
	        	System.out.println("Collided!");
	        	this.hasCollided  = true;
	        	this.toDestroy.add(this.getPosition());
	        	this.toDestroy.add(this.getPosition().up());
	        	this.toDestroy.add(this.getPosition().down());
	        	this.toDestroy.add(this.getPosition().north());
	        	this.toDestroy.add(this.getPosition().south());
	        	this.toDestroy.add(this.getPosition().east());
	        	this.toDestroy.add(this.getPosition().west());
	        }
		}
    }
    
    @Override
    public void onUpdate() {
    	if(!this.hasCollided) {
    		super.onUpdate();
    	}else {
    		if(this.antimatterRemaining > 0) {
    			ArrayList<BlockPos> toAdd = new ArrayList<BlockPos>();
    			ArrayList<BlockPos> toRemove = new ArrayList<BlockPos>();
    			
    			for(BlockPos pos : this.toDestroy) {
    				if(toAdd.size() < 30) {
    					int messiness = 2;
	    				this.world.setBlockToAir(pos);
	    				if(!this.world.getBlockState(pos.up()).getBlock().equals(Blocks.AIR) && r.nextInt(messiness) == 0) {
	    					if(this.antimatterRemaining > 0 && !this.hasBeenDestroyed.contains(pos.up())) {
	    						toAdd.add(pos.up());
	    						this.antimatterRemaining--;
	    					}
	    				}
	    				if(!this.world.getBlockState(pos.down()).getBlock().equals(Blocks.AIR) && r.nextInt(messiness) == 0) {
	    					if(this.antimatterRemaining > 0 && !this.hasBeenDestroyed.contains(pos.down())) {
	    						toAdd.add(pos.down());
	    						this.antimatterRemaining--;
	    					}
	    				}
	    				if(!this.world.getBlockState(pos.north()).getBlock().equals(Blocks.AIR) && r.nextInt(messiness) == 0) {
	    					if(this.antimatterRemaining > 0 && !this.hasBeenDestroyed.contains(pos.north())) {
	    						toAdd.add(pos.north());
	    						this.antimatterRemaining--;
	    					}
	    				}
	    				if(!this.world.getBlockState(pos.south()).getBlock().equals(Blocks.AIR) && r.nextInt(messiness) == 0) {
	    					if(this.antimatterRemaining > 0 && !this.hasBeenDestroyed.contains(pos.south())) {
	    						toAdd.add(pos.south());
	    						this.antimatterRemaining--;
	    					}
	    				}
	    				if(!this.world.getBlockState(pos.east()).getBlock().equals(Blocks.AIR) && r.nextInt(messiness) == 0) {
	    					if(this.antimatterRemaining > 0 && !this.hasBeenDestroyed.contains(pos.east())) {
	    						toAdd.add(pos.east());
	    						this.antimatterRemaining--;
	    					}
	    				}
	    				if(!this.world.getBlockState(pos.west()).getBlock().equals(Blocks.AIR) && r.nextInt(messiness) == 0) {
	    					if(this.antimatterRemaining > 0 && !this.hasBeenDestroyed.contains(pos.west())) {
	    						toAdd.add(pos.west());
	    						this.antimatterRemaining--;
	    					}
	    				}
	    				toRemove.add(pos);
    				}else {
    					break;
    				}
    			}
    			for(BlockPos pos : toAdd) {
    				this.toDestroy.add(pos);
    				this.hasBeenDestroyed.add(pos);
    			}
    			for(BlockPos pos : toRemove) {
    				this.toDestroy.remove(pos);
    			}
    			if(this.toDestroy.size() == 0) {
    	            this.world.setEntityState(this, (byte)3);
    	            this.setDead();
    	            System.out.println("Killed entity for lack of blocks");
    	            EntityLivingBase thrower = this.getThrower();
    	            if(thrower instanceof EntityPlayer) {
    	            	EntityPlayer player = (EntityPlayer) thrower;
    	            	player.sendMessage(new TextComponentString("Oops! Looks like a dud! Here's a complimentary replacement."));
    	            	world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, ItemGrenade.getCraftMatterWithUnits(totalAntimatter)));
    	            }
    			}
    		}else {
	            this.world.setEntityState(this, (byte)3);
	            this.setDead();
	            System.out.println("Killed entity");
    		}
    	}
    }
    
    private int getSkewedRandom() {
    	int res = 1;
    	if(r.nextInt(2) == 1) {
    		res++;
    	}
    	return res;
    }
}