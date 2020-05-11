package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemCompletion1BindingImpl extends ItemCompletion1Binding  {

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
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    @NonNull
    private final android.widget.TextView mboundView7;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemCompletion1BindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ItemCompletion1BindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
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
        if (BR.data == variableId) {
            setData((com.lkrh.storescontrol.bean.Completion1Bean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setData(@Nullable com.lkrh.storescontrol.bean.Completion1Bean Data) {
        this.mData = Data;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.data);
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
        java.lang.String dataCopname = null;
        java.lang.String dataCmocode = null;
        java.lang.String dataIqty = null;
        java.lang.String dataCopcode = null;
        java.lang.String dataCtuopan = null;
        com.lkrh.storescontrol.bean.Completion1Bean data = mData;
        java.lang.String dataDdate = null;
        java.lang.String dataCuser = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (data != null) {
                    // read data.copname
                    dataCopname = data.getCopname();
                    // read data.cmocode
                    dataCmocode = data.getCmocode();
                    // read data.iqty
                    dataIqty = data.getIqty();
                    // read data.copcode
                    dataCopcode = data.getCopcode();
                    // read data.ctuopan
                    dataCtuopan = data.getCtuopan();
                    // read data.ddate
                    dataDdate = data.getDdate();
                    // read data.cuser
                    dataCuser = data.getCuser();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, dataCopcode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, dataCopname);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, dataCmocode);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, dataDdate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, dataIqty);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, dataCtuopan);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, dataCuser);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): data
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}