# YAGNI Principle — You Aren’t Gonna Need It

## Introduction

Have you ever added a feature because you **might need it someday**?

Or created an abstraction for a use case that does not exist yet?

Or built extra flexibility that nobody has ever used?

If so, you have probably violated **YAGNI**:

> **YAGNI = You Aren’t Gonna Need It**

The idea is simple:

> **Do not build something until you actually need it.**

YAGNI helps keep software focused, lean, easier to understand, and easier to maintain.

---

# 1. What Is YAGNI?

YAGNI comes from **Extreme Programming (XP)**.

Its core idea is:

> **Implement things when you actually need them, not when you only predict that you may need them.**

In simple terms:

> **Build for today’s real requirements, not tomorrow’s imagined requirements.**

YAGNI does **not** mean:

- Write bad code.
- Ignore architecture.
- Ignore security.
- Avoid clean design.
- Never refactor.

It means:

> **Do not add speculative features, abstractions, layers, or flexibility without a real requirement.**

---

# 2. Real-World Example — Profile Image Upload

Imagine you are building a profile picture upload feature.

The current requirement is simple:

1. Accept an image.
2. Resize it to `300 x 300`.
3. Store it on the local filesystem.

That is all.

A developer might start thinking:

- What if we support videos later?
- What if we move to AWS S3?
- What if users upload 3D avatars?
- What if other teams need custom storage providers?
- What if we need a plugin system?

These are all **possible futures**, but they are not current requirements.

## Overengineered Version — YAGNI Violation

```java
public interface MediaHandler {
    void process(String filePath);
}
```

```java
public interface StorageProvider {
    void store(String filePath);

    byte[] retrieve(String fileName);

    void delete(String fileName);
}
```

```java
public class LocalStorageProvider
        implements StorageProvider {

    @Override
    public void store(String filePath) {
        System.out.println("Saving locally...");
    }

    @Override
    public byte[] retrieve(String fileName) {
        return new byte[0];
    }

    @Override
    public void delete(String fileName) {
        // not currently used
    }
}
```

```java
public class CloudStorageProvider
        implements StorageProvider {

    @Override
    public void store(String filePath) {
        // maybe later
    }

    @Override
    public byte[] retrieve(String fileName) {
        return null;
    }

    @Override
    public void delete(String fileName) {
        // maybe later
    }
}
```

```java
public class ImageHandler
        implements MediaHandler {

    private final StorageProvider storageProvider;

    public ImageHandler(StorageProvider storageProvider) {
        this.storageProvider = storageProvider;
    }

    @Override
    public void process(String filePath) {
        // resize image
        storageProvider.store(filePath);
    }
}
```

```java
public class MediaHandlerFactory {

    public static MediaHandler createImageHandler() {
        return new ImageHandler(
                new LocalStorageProvider()
        );
    }
}
```

Technically, this design is flexible.

But the real requirement was only:

```text
Accept image
    ↓
Resize image
    ↓
Store locally
```

We created multiple interfaces and classes for requirements that do not exist yet.

That is a classic **YAGNI violation**.

---

# 3. Simple Version — YAGNI Applied

A simpler solution:

```java
public class ProfileImageService {

    public void upload(String filePath) {

        String resizedImage =
                resizeImage(filePath);

        saveLocally(resizedImage);
    }

    private String resizeImage(String filePath) {

        System.out.println(
                "Resizing image to 300x300"
        );

        return filePath;
    }

    private void saveLocally(String filePath) {

        System.out.println(
                "Saving image locally"
        );
    }
}
```

This solution:

- Meets today’s requirement.
- Is easy to read.
- Is easy to test.
- Is easy to debug.
- Has no unused interfaces.
- Has no dead code.
- Has no speculative infrastructure.

If cloud storage becomes a real requirement later, refactor then.

If video upload becomes a real requirement later, extend then.

You will have better information when the requirement actually exists.

---

# 4. Why Premature Work Is Harmful

## 4.1 Wasted Time

Every speculative feature costs:

- Development time
- Code review time
- Testing time
- Documentation time
- Maintenance time

If users only need local image upload today, building an unused cloud adapter provides no current value.

## 4.2 Increased Complexity

Extra flexibility creates extra moving parts.

Instead of:

```text
ProfileImageService
```

you may end up with:

```text
MediaHandler
StorageProvider
ImageHandler
LocalStorageProvider
CloudStorageProvider
MediaHandlerFactory
MediaProcessingEngine
```

A new developer sees all of this and assumes every abstraction is necessary.

Even unused complexity becomes difficult to remove later.

## 4.3 Delayed Delivery

Suppose:

```text
Simple implementation
→ 1 afternoon
```

But the speculative architecture takes:

```text
Flexible architecture
→ 1 week
```

You delayed the feature users actually need for functionality nobody requested.

YAGNI reminds us:

> **Deliver real value before imaginary flexibility.**

## 4.4 Higher Maintenance Cost

Unused code is not free.

It still needs to:

- Compile.
- Be tested.
- Be updated.
- Be reviewed.
- Work with dependency upgrades.
- Be understood by future developers.

Dead code is still code you own.

---

# 5. A Second Real-World Example — Payment System

Suppose your application currently supports only Stripe.

Current requirement:

```text
Customer
   ↓
Stripe
   ↓
Payment processed
```

A simple implementation could be:

```java
public class PaymentService {

    private final StripeClient stripeClient;

    public PaymentService(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    public void pay(double amount) {
        stripeClient.charge(amount);
    }
}
```

This may be enough today.

Now imagine immediately creating:

```text
PaymentGateway
PaymentGatewayFactory
PaymentStrategy
PaymentProviderRegistry
StripeAdapter
PayPalAdapter
CryptoAdapter
BankTransferAdapter
```

even though Stripe is the only supported provider.

That may be premature.

## When the Requirement Changes

Later the business says:

> We now need PayPal too.

Now you have real evidence of variation.

Then introducing an interface may make sense:

```java
public interface PaymentGateway {
    void pay(double amount);
}
```

```java
public class StripePayment
        implements PaymentGateway {

    @Override
    public void pay(double amount) {
        System.out.println(
                "Processing Stripe payment"
        );
    }
}
```

```java
public class PayPalPayment
        implements PaymentGateway {

    @Override
    public void pay(double amount) {
        System.out.println(
                "Processing PayPal payment"
        );
    }
}
```

Now the abstraction solves a **real problem**.

That is the difference between:

```text
Speculative abstraction
```

and:

```text
Requirement-driven abstraction
```

---

# 6. YAGNI Does Not Mean “Never Plan Ahead”

YAGNI is not an excuse to ignore known requirements.

There is a major difference between:

```text
"What if we need this someday?"
```

and:

```text
"We know this requirement exists."
```

## Security and Compliance

If you are building a banking system, you may need:

- Audit logs
- Encryption
- Authentication
- Authorization
- Data retention rules

from day one.

These are not imaginary features.

They are real constraints.

So implementing them early does **not** violate YAGNI.

## Known Scalability Requirements

Suppose your contract says:

> The system must support 1 million requests per minute.

Then designing for scale is not speculation.

It is a requirement.

But if your small internal tool has 20 users and you design a globally distributed architecture because:

> “Maybe one day we will have millions of users.”

that may be YAGNI.

## Libraries and Frameworks

Reusable libraries often need more careful API design because many consumers may depend on them.

Even then:

> Start with the smallest useful API.

Expand based on real usage.

Do not expose ten configuration options when users currently need only two.

---

# 7. YAGNI vs Good Design

YAGNI does not mean this:

```java
public void checkout() {
    // 500 lines
}
```

just because:

> “We don't need architecture yet.”

You should still use:

- Clear naming
- Encapsulation
- Small focused methods
- Good separation of responsibilities
- Tests
- Proper error handling

YAGNI removes **speculative complexity**, not **useful structure**.

---

# 8. YAGNI and Interfaces

A common junior-developer question is:

> “Should I create an interface for every service?”

No.

An interface is useful when you have a reason.

For example:

```text
PaymentGateway
    ↑
 ┌──┴───┐
Stripe PayPal
```

This is useful because you have multiple implementations.

But:

```text
StudentNameProvider
    ↑
StudentNameProviderImpl
```

when there will realistically be only one implementation may add ceremony without value.

Ask:

> **What problem is this interface solving today?**

If you cannot answer clearly, it may be premature.

---

# 9. YAGNI and Factories

Factories are useful when object creation is complex or varies.

But this:

```java
public class EmailServiceFactory {

    public static EmailService create() {
        return new EmailService();
    }
}
```

when there is only one simple constructor:

```java
new EmailService();
```

may add unnecessary indirection.

Do not use a pattern just because you learned it.

Use a pattern because a real problem needs it.

---

# 10. Senior Developer Mental Model

Before adding a new feature, interface, abstraction, configuration option, or extension point, ask:

```text
Is this required today?

Who asked for it?

Is it part of a real requirement?

Does it solve a current problem?

Am I building this because I know
it is needed or because I imagine
it might be useful someday?

Can I add it later without unreasonable cost?
```

If the answer is:

> “We might need it someday.”

YAGNI says:

> **Wait.**

---

# 11. YAGNI + KISS + DRY

These three principles work together.

## YAGNI

```text
Don't build unnecessary future features.
```

## KISS

```text
Don't add unnecessary complexity.
```

## DRY

```text
Don't duplicate the same knowledge.
```

Together:

> **Build only what you need, keep it as simple as possible, and avoid repeating the same knowledge.**

---

# 12. Easy Comparison

| Principle | Main Question | Goal |
|---|---|---|
| **YAGNI** | Do we actually need this now? | Avoid speculative work |
| **KISS** | Is there a simpler solution? | Avoid unnecessary complexity |
| **DRY** | Am I maintaining the same knowledge in multiple places? | Avoid duplication |

---

# 13. Short Notes

```text
YAGNI
= You Aren't Gonna Need It
```

```text
Main Idea:
Don't build for imaginary future requirements.
Build for today's real requirements.
```

```text
Avoid:
→ Unused features
→ Speculative abstractions
→ Unused configuration
→ Interfaces with no real need
→ Factories for one simple implementation
→ Plugin systems without plugins
→ Dead code
```

```text
Do:
→ Implement current requirements
→ Keep code clean
→ Refactor when new requirements arrive
→ Add abstraction when real variation appears
→ Plan for known constraints
```

---

# 14. Key Points

- **YAGNI = You Aren’t Gonna Need It.**
- Build features when they are actually required.
- Avoid speculative architecture.
- Do not add flexibility simply because you may need it later.
- Every unused abstraction still has a maintenance cost.
- Simple code today can be refactored tomorrow.
- Future requirements are easier to design when you actually know them.
- Security, compliance, and contractual requirements are not YAGNI violations.
- Known scalability constraints should be considered early.
- YAGNI does not mean writing poor-quality code.
- Clean design for current requirements is still important.
- Use interfaces, factories, and patterns when they solve real problems.

---

# 15. Memory Trick

Use this question:

> **“Do I need this now?”**

If:

```text
YES
→ Build it.
```

If:

```text
MAYBE SOMEDAY
→ Don't build it yet.
```

The easiest way to memorize YAGNI:

> **YAGNI = Not needed now? Not built now.**

---

# Final Takeaway

A junior developer often thinks:

> “What if we need this later?”

A more experienced developer asks:

> **“What evidence do we have that we need this?”**

Good software engineering is not about predicting every possible future.

It is about building a clean solution for the real problem you have now, while keeping the code easy enough to change when the future actually arrives.

> **Build what you need. Refactor when you learn more.**
