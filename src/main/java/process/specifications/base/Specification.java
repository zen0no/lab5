package process.specifications.base;

public interface Specification<T> {
    public boolean isSatisfiedBy(T candidate);
    Specification<T> And(Specification<T> other);
    Specification<T> Or(Specification<T> other);
    Specification<T> Not(Specification<T> other);

}
