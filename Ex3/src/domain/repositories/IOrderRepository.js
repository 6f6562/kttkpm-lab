class IOrderRepository {
    async save(order) {
        throw new Error("Method 'save(order)' must be implemented.");
    }
}

module.exports = IOrderRepository;
