package process.specifications.base;

public abstract class CompositeSpecification<T> implements Specification<T> {
    public abstract boolean isSatisfiedBy(T candidate);

    @Override
    public Specification<T> And(Specification<T> other){
        return new AndSpecification<T>(this, other);
    }

    @Override
    public Specification<T> Or(Specification<T> other){
        return new OrSpecification<T>(this, other);
    }

    @Override
    public  Specification<T> Not(Specification<T> other){
        return new NotSpecification<T>(this);
    }
}
