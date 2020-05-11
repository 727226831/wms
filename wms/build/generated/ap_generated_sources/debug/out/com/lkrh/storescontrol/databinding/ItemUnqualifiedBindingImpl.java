package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemUnqualifiedBindingImpl extends ItemUnqualifiedBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemUnqualifiedBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ItemUnqualifiedBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvCcode.setTag(null);
        this.tvCinvcode.setTag(null);
        this.tvCinvname.setTag(null);
        this.tvCinvstd.setTag(null);
        this.tvCmocode.setTag(null);
        this.tvCopcode.setTag(null);
        this.tvCopname.setTag(null);
        this.tvCtuopan.setTag(null);
        this.tvCuser.setTag(null);
        this.tvIqty.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.unqualifiedBean == variableId) {
            setUnqualifiedBean((com.lkrh.storescontrol.bean.UnqualifiedBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUnqualifiedBean(@Nullable com.lkrh.storescontrol.bean.UnqualifiedBean UnqualifiedBean) {
        this.mUnqualifiedBean = UnqualifiedBean;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.unqualifiedBean);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String unqualifiedBeanCtuopan = null;
        java.lang.String unqualifiedBeanCinvstd = null;
        java.lang.String unqualifiedBeanCinvcode = null;
        java.lang.String unqualifiedBeanCmocode = null;
        java.lang.String unqualifiedBeanCinvname = null;
        java.lang.String unqualifiedBeanCuser = null;
        java.lang.String unqualifiedBeanCopcode = null;
        java.lang.String unqualifiedBeanIqty = null;
        com.lkrh.storescontrol.bean.UnqualifiedBean unqualifiedBean = mUnqualifiedBean;
        java.lang.String unqualifiedBeanCcode = null;
        java.lang.String unqualifiedBeanCopname = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (unqualifiedBean != null) {
                    // read unqualifiedBean.ctuopan
                    unqualifiedBeanCtuopan = unqualifiedBean.getCtuopan();
                    // read unqualifiedBean.cinvstd
                    unqualifiedBeanCinvstd = unqualifiedBean.getCinvstd();
                    // read unqualifiedBean.cinvcode
                    unqualifiedBeanCinvcode = unqualifiedBean.getCinvcode();
                    // read unqualifiedBean.cmocode
                    unqualifiedBeanCmocode = unqualifiedBean.getCmocode();
                    // read unqualifiedBean.cinvname
                    unqualifiedBeanCinvname = unqualifiedBean.getCinvname();
                    // read unqualifiedBean.cuser
                    unqualifiedBeanCuser = unqualifiedBean.getCuser();
                    // read unqualifiedBean.copcode
                    unqualifiedBeanCopcode = unqualifiedBean.getCopcode();
                    // read unqualifiedBean.iqty
                    unqualifiedBeanIqty = unqualifiedBean.getIqty();
                    // read unqualifiedBean.ccode
                    unqualifiedBeanCcode = unqualifiedBean.getCcode();
                    // read unqualifiedBean.copname
                    unqualifiedBeanCopname = unqualifiedBean.getCopname();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCcode, unqualifiedBeanCcode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCinvcode, unqualifiedBeanCinvcode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCinvname, unqualifiedBeanCinvname);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCinvstd, unqualifiedBeanCinvstd);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCmocode, unqualifiedBeanCmocode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCopcode, unqualifiedBeanCopcode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCopname, unqualifiedBeanCopname);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCtuopan, unqualifiedBeanCtuopan);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCuser, unqualifiedBeanCuser);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvIqty, unqualifiedBeanIqty);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): unqualifiedBean
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}