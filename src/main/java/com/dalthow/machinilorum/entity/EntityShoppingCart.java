package com.dalthow.machinilorum.entity;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class EntityShoppingCart extends Entity
{
    // Declaration of some variables
    private boolean isCartEmpty;
  
    private int cartPosRotationIncrements;

    // Declaration of the carts position and rotation.
    private double xCart;
    private double yCart;
    private double zCard;
    
    private double cartYaw;
    private double cartPitch;

    // Declaration of the velocities. This get's ignored on the server.
    @SideOnly(Side.CLIENT)
    private double velocityX;
    
    @SideOnly(Side.CLIENT)
    private double velocityY;
    
    @SideOnly(Side.CLIENT)
    private double velocityZ;

    // Constructor.
    public EntityShoppingCart(World world)
    {
        super(world);
        
        isCartEmpty = true;
        preventEntitySpawning = true;
        
        // TODO: Fix the bounding box.
        
        setSize(1.3F, 1.3F);
    }

    // Tells the entity class that it should make the walking noise and animation.
    protected boolean canTriggerWalking()
    {
        return false;
    }

    // Gets called on creation.
    protected void entityInit()
    {
        dataWatcher.addObject(17, 0);
        dataWatcher.addObject(18, 1);
        dataWatcher.addObject(19, 0.0F);
    }
   
    // Returns a bounding box used to collide the entity with other entities and blocks
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }
    
    // Returns the bounding box for this entity.
    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }

    // Returns true if this entity should push and be pushed by other entities when colliding.
    public boolean canBePushed()
    {
        return true;
    }

    // Puts the player further down.
    public double getMountedYOffset()
    {
        return 0.42D;
    }

    // Called when the entity is attacked.
    public boolean attackEntityFrom(DamageSource source, float par1)
    {
        if(isEntityInvulnerable())
            return false;
        
        else if(!worldObj.isRemote && !isDead)
        {
            setForwardDirection(-getForwardDirection());
            setTimeSinceHit(10);
            setDamageTaken(getDamageTaken() + par1 * 10.0F);
            setBeenAttacked();
            
            boolean flag = source.getEntity() instanceof EntityPlayer && ((EntityPlayer)source.getEntity()).capabilities.isCreativeMode;

            if(flag || getDamageTaken() > 40.0F)
            {
                if(riddenByEntity != null)
                    riddenByEntity.mountEntity(this);

                if(!flag)
                    func_145778_a(Main.itemShoppingCart, 1, 0.0F);

                setDead();
            }

            return true;
        }
        
        else
            return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRiderSit()
    {    	
    	return true;
    }

    // Setups the entity to do the hurt animation.
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        setForwardDirection(-getForwardDirection());
        setTimeSinceHit(10);
        setDamageTaken(getDamageTaken() * 11.0F);
    }

    // Returns true if other Entities should be prevented from moving through this Entity.
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    // Sets the position and rotation.
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double par1, double par2, double par3, float par4, float par5, int par6)
    {
        if(isCartEmpty)
            cartPosRotationIncrements = par6 + 5;
        
        else
        {
            double var1 = par1 - posX;
            double var2 = par2 - posY;
            double var3 = par3 - posZ;
            double var4 = var1 * var1 + var2 * var2 + var3 * var3;

            if(var4 <= 1.0D)
                return;

            cartPosRotationIncrements = 3;
        }

        xCart = par1;
        yCart = par2;
        zCard = par3;
        
        cartYaw = (double)par4;
        cartPitch = (double)par5;
        
        motionX = velocityX;
        motionY = velocityY;
        motionZ = velocityZ;
    }

    // Sets the velocity to the arguments.
    @SideOnly(Side.CLIENT)
    public void setVelocity(double par1, double par2, double par5)
    {
        velocityX = motionX = par1;
        velocityY = motionY = par2;
        velocityZ = motionZ = par5;
    }

    // Called to update the entity.
    public void onUpdate()
    {
        super.onUpdate();
        
        if(getTimeSinceHit() > 0)
            setTimeSinceHit(getTimeSinceHit() - 1);

        if(getDamageTaken() > 0.0F)
            setDamageTaken(getDamageTaken() - 1.0F);

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
       
        double var1 = 0.0D;

        Math.sqrt(motionX * motionX + motionZ * motionZ);
        
        double var2;
        double var3;
        
        int var4;

        double var5;
        double var6;
        
        if(worldObj.isRemote && isCartEmpty)
        {
            if(cartPosRotationIncrements > 0)
            {
                var2 = posX + (xCart - posX) / (double)cartPosRotationIncrements;
                var3 = posY + (yCart - posY) / (double)cartPosRotationIncrements;
                var5 = posZ + (zCard - posZ) / (double)cartPosRotationIncrements;
                var6 = MathHelper.wrapAngleTo180_double(cartYaw - (double)rotationYaw);
               
                rotationYaw = (float)((double)rotationYaw + var6 / (double)cartPosRotationIncrements);
                rotationPitch = (float)((double)rotationPitch + (cartPitch - (double)rotationPitch) / (double)cartPosRotationIncrements);
                
                --cartPosRotationIncrements;
                
                setPosition(var2, var3, var5);
                setRotation(rotationYaw, rotationPitch);
            }
            
            else
            {
                var2 = posX + motionX;
                var3 = posY + motionY;
                var5 = posZ + motionZ;

                setPosition(var2, var3, var5);

                if(onGround)
                {
                    motionX *= 0.5D;
                    motionY *= 0.5D;
                    motionZ *= 0.5D;
                }

                motionX *= 0.9900000095367432D;
                motionY *= 0.949999988079071D;
                motionZ *= 0.9900000095367432D;
            }
        }
        
        else
        {
            if(var1 < 1.0D)
            {
                var2 = var1 * 2.0D - 1.0D;
                motionY += 0.03999999910593033D * var2;
            }
            
            else
            {
                if(motionY < 0.0D)
                    motionY /= 2.0D;

                motionY += 0.007000000216066837D;
            }

            if(riddenByEntity != null && riddenByEntity instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)riddenByEntity;
                
                float var7 = riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F;
                
                motionX += -Math.sin((double)(var7 * (float)Math.PI / 180.0F))  * (double)entitylivingbase.moveForward * 1D;
                motionZ += Math.cos((double)(var7 * (float)Math.PI / 180.0F)) * (double)entitylivingbase.moveForward * 1D;
            }

            var2 = Math.sqrt(motionX * motionX + motionZ * motionZ);

            if(var2 > 0.35D)
            {
                var3 = 0.35D / var2;
                motionX *= var3;
                motionZ *= var3;
            }

            for(int i = 0; i < 4; ++i)
            {
                int var8 = MathHelper.floor_double(posX + ((double)(i % 2) - 0.5D) * 0.8D);
                
                var4 = MathHelper.floor_double(posZ + ((double)(i / 2) - 0.5D) * 0.8D);

                for(int j = 0; j < 2; ++j)
                {
                    int var9 = MathHelper.floor_double(posY) + j;
                    
                    Block block = worldObj.getBlock(var8, var9, var4);

                    if(block == Blocks.snow_layer)
                    {
                        worldObj.setBlockToAir(var8, var9, var4);
                        isCollidedHorizontally = false;
                    }
                    
                    else if(block == Blocks.waterlily)
                    {
                        worldObj.func_147480_a(var8, var9, var4, true);
                        isCollidedHorizontally = false;
                    }
                }
            }

            if(onGround)
            {
                motionX *= 0.5D;
                motionY *= 0.5D;
                motionZ *= 0.5D;
            }

            moveEntity(motionX, motionY, motionZ);

            rotationPitch = 0.0F;
            
            var3 = (double)rotationYaw;
            var5 = prevPosX - posX;
            var6 = prevPosZ - posZ;

            if(var5 * var5 + var6 * var6 > 0.001D)
                var3 = (double)((float)(Math.atan2(var6, var5) * 180.0D / Math.PI));

            double var10 = MathHelper.wrapAngleTo180_double(var3 - (double)rotationYaw);

            if(var10 > 20.0D)
                var10 = 20.0D;

            if(var10 < -20.0D)
                var10 = -20.0D;

            rotationYaw = (float)((double)rotationYaw + var10);
            setRotation(rotationYaw, rotationPitch);

            if(!worldObj.isRemote)
            {
                List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

                if(list != null && !list.isEmpty())
                    for(int i = 0; i < list.size(); ++i)
                    {
                        Entity entity = (Entity)list.get(i);

                        if(entity != riddenByEntity && entity.canBePushed() && entity instanceof EntityShoppingCart)
                            entity.applyEntityCollision(this);
                    }

                if(riddenByEntity != null && riddenByEntity.isDead)
                    riddenByEntity = null;
            }
        }
    }

    // Updates where the player sits in the cart.
    public void updateRiderPosition()
    {
        if(riddenByEntity != null)
            riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ + 0.4);
    }

    protected void writeEntityToNBT(NBTTagCompound tag) 
    {
    	// TODO: Add cart rotation.
    }

    protected void readEntityFromNBT(NBTTagCompound tag)
    {
    	// TODO: Add cart rotation.
    }

    // Puts down a shadow under the cart.
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 1.0F;
    }

    // First layer of player interaction.
    public boolean interactFirst(EntityPlayer player)
    {
        if(riddenByEntity != null && riddenByEntity instanceof EntityPlayer && riddenByEntity != player)
            return true;
        
        else
        {
            if(!worldObj.isRemote)
                player.mountEntity(this);

            return true;
        }
    }
    
    // Sets the damage taken from the last hit.
    public void setDamageTaken(float par1)
    {
        dataWatcher.updateObject(19, Float.valueOf(par1));
    }

    // Gets the damage taken from the last hit.
    public float getDamageTaken()
    {
        return dataWatcher.getWatchableObjectFloat(19);
    }

    // Sets the time to count down from since the last time entity was hit.
    public void setTimeSinceHit(int par1)
    {
        dataWatcher.updateObject(17, Integer.valueOf(par1));
    }

    // Gets the time since the last hit.
    public int getTimeSinceHit()
    {
        return dataWatcher.getWatchableObjectInt(17);
    }

    // Sets the forward direction of the entity.
    public void setForwardDirection(int par1)
    {
        dataWatcher.updateObject(18, Integer.valueOf(par1));
    }

    // Gets the forward direction of the entity.
    public int getForwardDirection()
    {
        return dataWatcher.getWatchableObjectInt(18);
    }

    // True if the player is not in the cart.
    @SideOnly(Side.CLIENT)
    public void setIsCartEmpty(boolean par1)
    {
        isCartEmpty = par1;
    }

    // Checks if the user is triggering movement forwards or backwards.
    public boolean isMoving()
    {
    	if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_S))
            return true;
    	
    	return false;
    }
}